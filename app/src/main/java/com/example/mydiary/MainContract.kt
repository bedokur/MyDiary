package com.example.mydiary

import com.example.mydiary.baseinterfaces.BasePresenter
import com.example.mydiary.baseinterfaces.BaseView

interface MainContract {
    interface Presenter : BasePresenter {
        fun showTodoItems()
    }

    interface View : BaseView<Presenter> {
        fun onDayClicked()
    }
}