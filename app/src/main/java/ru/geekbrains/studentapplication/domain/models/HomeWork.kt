package ru.geekbrains.studentapplication.domain.models

data class HomeWork(
    val id: Int,
    val title: String,
    val icon: Int,
    val deadLine: String,
    val work: String,
    val tagIconOne: Int,
    val tagIconTwo: Int
)