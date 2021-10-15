package com.example.mydiary.di

import com.example.mydiary.ui.mainActivity.MainContract
import com.example.mydiary.ui.mainActivity.MainPresenter
import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.ui.createActivity.CreateContract
import com.example.mydiary.ui.createActivity.CreatePresenter
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