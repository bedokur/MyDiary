package com.example.mydiary.di

import android.content.Context
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

@Module
class AppModule(private val application: MyDiaryApp) {

    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRepository(context: Context): TodoRepository {
        return TodoRepository(context)
    }
}

