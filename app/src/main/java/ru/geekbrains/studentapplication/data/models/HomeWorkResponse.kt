package ru.geekbrains.studentapplication.data.models

data class HomeWorkResponse(
    val id: Int,
    val title: String,
    val icon: Int,
    val deadLine: String,
    val work: String,
    val tagIconOne: Int,
    val tagIconTwo: Int
)