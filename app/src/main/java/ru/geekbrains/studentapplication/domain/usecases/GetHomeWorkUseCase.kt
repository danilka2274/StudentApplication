package ru.geekbrains.studentapplication.domain.usecases

import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.HomeWorks
import ru.geekbrains.studentapplication.domain.repository.Repository

class GetHomeWorkUseCase(private val repository: Repository) {
    suspend fun execute(): AppState<HomeWorks> =
        repository.getHomeWork()
}