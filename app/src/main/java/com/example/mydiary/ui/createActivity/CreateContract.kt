package com.example.mydiary.ui.createActivity

import com.example.mydiary.baseinterfaces.BasePresenter

interface CreateContract {
    interface Presenter : BasePresenter {
        fun peekStartDays(year: Int, month: Int, datOfMonth: Int)
        fun peekStartTime(hour: Int, minute: Int)
        fun peekFinishTime(hour: Int, minute: Int)
        fun saveTodo(name: String, description: String)
    }

    interface View {
        fun showDateStart(dateStart: String)
        fun showDateFinish(dateFinish: String)
        fun showError(text: String)
    }
}