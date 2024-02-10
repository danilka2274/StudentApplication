package ru.geekbrains.studentapplication.domain.usecases

import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Exam
import ru.geekbrains.studentapplication.domain.repository.Repository

class GetExamUseCase(private val repository: Repository) {
    suspend fun execute(): AppState<Exam> =
        repository.getExamDate()
}