package com.example.mydiary.models


data class TodoModel(
    val id: Int,
    val date_start: Long,
    val date_finish: Long,
    val name: String,
    val description: String
)
