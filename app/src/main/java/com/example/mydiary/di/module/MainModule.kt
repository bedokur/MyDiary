package com.example.mydiary.di.module

import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.ui.mainActivity.MainContract
import com.example.mydiary.ui.mainActivity.MainPresenter
import com.example.mydiary.utils.Utils
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val view: MainContract.View) {
    @Provides
    fun provideView(): MainContract.View {
        return view
    }

    @Provides
    fun providePresenter(repository: TodoRepository, utils: Utils): MainContract.Presenter {
        return MainPresenter(view, repository, utils)
    }
    @Provides
    fun provideUtils(): Utils {
        return Utils()
    }
}

