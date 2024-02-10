package ru.geekbrains.studentapplication.data.repository.datasource

import ru.geekbrains.studentapplication.data.mockGetExam
import ru.geekbrains.studentapplication.data.mockGetHomWork
import ru.geekbrains.studentapplication.data.mockGetLessons
import ru.geekbrains.studentapplication.data.models.FacultativeResponse
import ru.geekbrains.studentapplication.data.models.LessonResponse
import ru.geekbrains.studentapplication.data.tags
import ru.geekbrains.studentapplication.domain.AppState
import ru.geekbrains.studentapplication.domain.models.Exam
import ru.geekbrains.studentapplication.domain.models.Facultative
import ru.geekbrains.studentapplication.domain.models.HomeWork
import ru.geekbrains.studentapplication.domain.models.HomeWorks
import ru.geekbrains.studentapplication.domain.models.Lesson
import ru.geekbrains.studentapplication.domain.models.Lessons

class MockDataSourceImpl : DataSource {
    override suspend fun getLessons(): AppState<Lessons> {
        val result = mockGetLessons()
        return AppState.Success<Lessons>(Lessons(
            data = result.map {
                when (it) {
                    is FacultativeResponse -> {
                        Facultative(
                            id = it.id,
                            title = it.title,
                            icon = it.icon,
                            scheduler = it.scheduler,
                            teacher = it.teacher,
                            tagIconOne = it.tagIconOne,
                            tagIconTwo = it.tagIconTwo,
                            tagIconThree = it.tagIconThree,
                            description = it.description,
                            isTop = it.isTop
                        )
                    }
                    is LessonResponse -> {
                        Lesson(
                            id = it.id,
                            title = it.title,
                            icon = it.icon,
                            scheduler = it.scheduler,
                            useRemote = it.useRemote,
                            teacher = it.teacher,
                            isTop = it.isTop
                        )
                    }
                    else -> {
                        throw Exception("Unknown type")
                    }
                }
            }
        ))
    }

    override suspend fun getHomeWork(): AppState<HomeWorks> {
        val result = mockGetHomWork()
        return AppState.Success<HomeWorks>(HomeWorks(
            data = result.map {
                HomeWork(
                    id = it.id,
                    title = it.title,
                    icon = it.icon,
                    deadLine = it.deadLine,
                    work = it.work,
                    tagIconOne = tags.first(),
                    tagIconTwo = tags.last()
                )
            }
        ))
    }

    override suspend fun getExamDate(): AppState<Exam> {
        val result = mockGetExam()
        return AppState.Success<Exam>(
            Exam(
                date = result.date
            )
        )
    }
}