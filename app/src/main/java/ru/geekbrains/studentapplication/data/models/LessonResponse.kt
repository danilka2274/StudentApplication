package ru.geekbrains.studentapplication.data.models

import ru.geekbrains.studentapplication.domain.models.Lessonable
import ru.geekbrains.studentapplication.domain.models.Schedulers

data class LessonResponse(
    val id: Int,
    val title: String,
    val icon: Int,
    val scheduler: Schedulers,
    val teacher: String,
    val useRemote: Boolean = false,
    val isTop: Boolean = false
) : Lessonable