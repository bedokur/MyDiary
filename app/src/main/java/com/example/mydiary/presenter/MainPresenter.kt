package com.example.mydiary.presenter

import com.example.mydiary.ui.MainContract
import com.example.mydiary.models.TodoModel
import com.example.mydiary.repository.TodoRepository


class MainPresenter (var view: MainContract.View?, var repository: TodoRepository) : MainContract.Presenter {


    override fun showTodoItems() {
        val list = listOf(
            TodoModel(
                1, 3241341L, 32435324L, "name1", "desc1"
            ), TodoModel(
                1, 3452552L, 23414324L, "name2", "desc2"
            )
        )
        repository.writeJson(list)
        val a = repository.readJson()
    }

    override fun onDestroy() {
        this.view = null
    }
}