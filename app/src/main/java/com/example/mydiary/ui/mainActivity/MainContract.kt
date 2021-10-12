package com.example.mydiary.ui.mainActivity

import com.example.mydiary.baseinterfaces.BasePresenter
import com.example.mydiary.models.TodoModel

interface MainContract {
    interface Presenter : BasePresenter {
        fun showTodoItems(year: Int, month: Int, dayOfMonth: Int)
    }

    interface View {
        fun showTodoList(listForView: List<TodoModel>)
        fun showError(text: String?)
    }
}