package com.example.mydiary.baseinterfaces

interface BaseView<T> {
    fun setPresenter(presenter: T)
}