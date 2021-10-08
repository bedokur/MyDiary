package com.example.mydiary.ui

import com.example.mydiary.baseinterfaces.BasePresenter

interface MainContract {
    interface Presenter : BasePresenter {
        fun showTodoItems()
    }

    interface View  {
        fun showTodoList()
    }
}