package ru.geekbrains.studentapplication.domain.models

data class Facultative(
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