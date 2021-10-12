package com.example.mydiary.ui.detailsActivity

import com.example.mydiary.models.TodoModel

interface Navigator {
    fun navigate(item: TodoModel)
}