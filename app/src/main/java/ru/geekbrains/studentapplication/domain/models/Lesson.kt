package ru.geekbrains.studentapplication.domain.models

data class Lesson(
    val id: Int,
    val title: String,
    val icon: Int,
    val scheduler: Schedulers,
    val teacher: String,
    val useRemote: Boolean = false,
    val isTop: Boolean = false
) : Lessonable