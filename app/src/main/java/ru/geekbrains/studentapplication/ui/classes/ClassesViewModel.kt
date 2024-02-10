package ru.geekbrains.studentapplication.ui.classes

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Response
import ru.geekbrains.studentapplication.domain.usecases.GetLessonUseCase
import ru.geekbrains.studentapplication.ui.base.BaseViewModel

class ClassesViewModel(
    private val lessonsLiveData: MutableLiveData<AppState<Response>>
    = MutableLiveData<AppState<Response>>(),
    private val getLessonUseCase: GetLessonUseCase,
) : BaseViewModel() {

    fun getLessonsLiveData() = lessonsLiveData

    fun getLessons(): Job =
        viewModelScopeCoroutine.launch {
            getLessonsLiveData().postValue(
                getLessonUseCase.execute()
            )
        }

    override fun handleError(throwable: Throwable) {}
}