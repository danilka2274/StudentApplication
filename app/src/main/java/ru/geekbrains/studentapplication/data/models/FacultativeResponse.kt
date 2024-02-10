package ru.geekbrains.studentapplication.data.models

import ru.geekbrains.studentapplication.domain.models.Lessonable
import ru.geekbrains.studentapplication.domain.models.Schedulers

data class FacultativeResponse(
    val id: Int,
    val title: String,
    val icon: Int,
    val scheduler: Schedulers,
    val teacher: String,
    val tagIconOne: Int,
    val tagIconTwo: Int,
    val tagIconThree: Int,
    val description: String,
    val isTop: Boolean = false
) : Lessonable