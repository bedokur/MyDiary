package com.example.mydiary.di

import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.ui.createActivity.CreateContract
import com.example.mydiary.ui.createActivity.CreatePresenter
import dagger.Module
import dagger.Provides

@Module
class CreateModule(private val view: CreateContract.View){
    @Provides
    fun provideView(): CreateContract.View{
        return view
    }
    @Provides
    fun providesPresenter(repository: TodoRepository) : CreateContract.Presenter{
        return CreatePresenter(view, repository)
    }
}