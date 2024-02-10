package ru.geekbrains.studentapplication.domain.models

data class ExamTime(
    val dayFirst: String,
    val daySecond: String,
    val hourFirst: String,
    val hourSecond: String,
    val minuteFirst: String,
    val minuteSecond: String
) : Response