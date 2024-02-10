package ru.geekbrains.studentapplication.data.repository

import ru.geekbrains.studentapplication.data.repository.datasource.DataSource
import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Exam
import ru.geekbrains.studentapplication.domain.models.HomeWorks
import ru.geekbrains.studentapplication.domain.models.Lessons
import ru.geekbrains.studentapplication.domain.repository.Repository

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override suspend fun getLessons(): AppState<Lessons> =
        dataSource.getLessons()

    override suspend fun getHomeWork(): AppState<HomeWorks> =
        dataSource.getHomeWork()

    override suspend fun getExamDate(): AppState<Exam> =
        dataSource.getExamDate()
}