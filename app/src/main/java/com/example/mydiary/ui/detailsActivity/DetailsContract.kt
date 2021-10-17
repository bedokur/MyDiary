package com.example.mydiary.ui.detailsActivity

import com.example.mydiary.baseinterfaces.BasePresenter
import com.example.mydiary.models.TodoModel

interface DetailsContract {
    interface Presenter : BasePresenter {
        fun getTodoDetails(id: Int)
    }

    interface View {
        fun showDetails(item: TodoModel?)
    }
}