package com.example.mydiary.di.module

import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.ui.detailsActivity.DetailsContract
import com.example.mydiary.ui.detailsActivity.DetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailsModule(private val view: DetailsContract.View) {
    @Provides
    fun provideView(): DetailsContract.View {
        return view
    }

    @Provides
    fun providesPresenter(repository: TodoRepository): DetailsContract.Presenter {
        return DetailsPresenter(view, repository)
    }
}

