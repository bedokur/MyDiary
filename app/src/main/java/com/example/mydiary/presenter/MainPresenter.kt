package com.example.mydiary.presenter

import com.example.mydiary.MainContract

class MainPresenter(var view: MainContract.View?) : MainContract.Presenter {
    override fun showTodoItems() {
    }

    override fun onDestroy() {
        this.view = null
    }
}