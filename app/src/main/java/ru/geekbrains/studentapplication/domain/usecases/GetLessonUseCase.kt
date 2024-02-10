package ru.geekbrains.studentapplication.domain.usecases

import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Lessons
import ru.geekbrains.studentapplication.domain.repository.Repository

class GetLessonUseCase(private val repository: Repository) {
    suspend fun execute(): AppState<Lessons> =
        repository.getLessons()
}