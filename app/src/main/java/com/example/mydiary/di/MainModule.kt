package com.example.mydiary.di

import com.example.mydiary.ui.MainContract
import com.example.mydiary.presenter.MainPresenter
import com.example.mydiary.repository.TodoRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val view: MainContract.View) {
    @Provides
    fun provideView(): MainContract.View {
        return view
    }

    @Provides
    fun providePresenter(repository: TodoRepository): MainContract.Presenter {
        return MainPresenter(view, repository)
    }
}