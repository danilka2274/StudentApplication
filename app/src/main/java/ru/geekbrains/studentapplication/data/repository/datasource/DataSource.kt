package ru.geekbrains.studentapplication.data.repository.datasource

import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Exam
import ru.geekbrains.studentapplication.domain.models.HomeWorks
import ru.geekbrains.studentapplication.domain.models.Lessons

interface DataSource {
    suspend fun getLessons(): AppState<Lessons>
    suspend fun getHomeWork(): AppState<HomeWorks>
    suspend fun getExamDate(): AppState<Exam>
}