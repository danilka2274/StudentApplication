package ru.geekbrains.studentapplication.ui.classes


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import ru.geekbrains.studentapplication.R
import ru.geekbrains.studentapplication.databinding.FragmentClassesBinding
import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Lessonable
import ru.geekbrains.studentapplication.domain.models.Lessons
import ru.geekbrains.studentapplication.ui.base.BaseFragment
import ru.geekbrains.studentapplication.ui.classes.adapter.Adapter
import ru.geekbrains.studentapplication.utils.getCurrentDate
import ru.geekbrains.studentapplication.utils.upperMonthInString
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassesFragment : BaseFragment<FragmentClassesBinding>(R.layout.fragment_classes),
    Adapter.Delegate {

    private val viewModel: ClassesViewModel by viewModel()
    private val adapter by lazy { Adapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerSetting()
        viewModel.getLessons()
        setCurrentDate()
    }

    private fun setCurrentDate() {
        val dateString = getCurrentDate()
        viewBinding.date.text = String
            .format(CURRENT_DATE_STRING_TEMPLATE, upperMonthInString(dateString))
    }

    private fun initRecyclerSetting() {
        viewBinding.rvClasses.also { recycler ->
            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
        }
    }

    override fun initListeners() {}

    override fun initObservers() {
        viewModel.getLessonsLiveData()
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }
    }

    override fun renderSuccess(result: AppState.Success<*>) {
        when (val res = result.data) {
            is Lessons -> {
                adapter.setItems(res.data as ArrayList<Lessonable>)
            }
        }
    }

    override fun showLoading(isShow: Boolean) {}

    override fun showError(throwable: Throwable) {}

    override fun onLessonItemPicked(lesson: Lessonable) {}

    override fun onRunSkype() {
        try {
            val skype = Intent(ACTION_INTENT_STRING)
            skype.data = Uri.parse(SKYPE_URI_STRING)
            activity?.startActivity(skype)
        } catch (e: ActivityNotFoundException) {
        }
    }

    companion object {
        const val CURRENT_DATE_STRING_TEMPLATE = "Сегодня, %s"
        const val ACTION_INTENT_STRING = "android.intent.action.VIEW"
        const val SKYPE_URI_STRING = "skype:"
    }
}