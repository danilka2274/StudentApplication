package ru.geekbrains.studentapplication.domain.models

data class Schedulers(
    val start: String,
    val end: String
) {
    override fun toString(): String = "$start - $end"
}
