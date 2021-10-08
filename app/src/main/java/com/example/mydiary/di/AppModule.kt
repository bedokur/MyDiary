package com.example.mydiary.di

import android.content.Context
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.repository.TodoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Module
class AppModule(private val application: MyDiaryApp) {

    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun provideRepository(context: Context): TodoRepository {
        return TodoRepository(context)
    }
}

