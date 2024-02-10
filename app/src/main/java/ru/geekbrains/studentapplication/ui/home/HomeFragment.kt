package ru.geekbrains.studentapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.databinding.FragmentHomeBinding
import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.*
import ru.geekbrains.studentapplication.ui.base.BaseFragment
import ru.geekbrains.studentapplication.ui.home.adapter.HomeWorkAdapter
import ru.geekbrains.studentapplication.ui.home.adapter.LessonAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    LessonAdapter.Delegate, HomeWorkAdapter.Delegate {

    private val viewModel: HomeViewModel by viewModel()
    private val lessonAdapter by lazy { LessonAdapter(this) }
    private val homeWorkAdapter by lazy { HomeWorkAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerSetting()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    override fun onPause() {
        super.onPause()
        viewModel.clear()
    }

    private fun initRecyclerSetting() {
        viewBinding.rvClasses.also { recycler ->
            recycler.adapter = lessonAdapter
            recycler.setHasFixedSize(true)
        }

        viewBinding.rvHomework.also { recycler ->
            recycler.adapter = homeWorkAdapter
            recycler.setHasFixedSize(true)
        }
    }

    override fun initListeners() {}

    override fun initObservers() {
        viewModel.getLessonsLiveData()
            .observe(this) { res -> renderData(result = res) }

        viewModel.getCountDownLiveData()
            .observe(this) { res -> renderCountDown(result = res) }
    }

    private fun renderCountDown(result: AppState<ExamTime>) {
        if (result is AppState.Success) {
            setExamDate(result.data)
        }
    }

    private fun setExamDate(it: ExamTime) {
        try {
            with(viewBinding) {
                dayFirst.text = it.dayFirst
                daySecond.text = it.daySecond
                hourFirst.text = it.hourFirst
                hourSecond.text = it.hourSecond
                minuteFirst.text = it.minuteFirst
                minuteSecond.text = it.minuteSecond
            }
        } catch (err: Exception) {
            Log.d("VVVV", "${err.localizedMessage}")
        }
    }

    override fun renderSuccess(result: AppState.Success<*>) {
        when (val res = result.data) {
            is HomeWorks -> {
                homeWorkAdapter.setItems(res.data as ArrayList<HomeWork>)
            }
            is Lessons -> {
                lessonAdapter.setItems(res.data as ArrayList<Lessonable>)
            }
        }
    }

    override fun showLoading(isShow: Boolean) {}

    override fun showError(throwable: Throwable) {}

    override fun onLessonItemPicked(lessonResponse: Lessonable) {}

    override fun onHomeWorkItemPicked(homeWork: HomeWork) {}
}