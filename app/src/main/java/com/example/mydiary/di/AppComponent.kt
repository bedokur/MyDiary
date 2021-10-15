package com.example.mydiary.di

import com.example.mydiary.ui.createActivity.CreateTodoActivity
import com.example.mydiary.ui.mainActivity.MainActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun plus(mainModule: MainModule): MainComponent
    fun plus(createModule: CreateModule): CreateComponent
}

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(view: MainActivity)
}

@ActivityScope
@Subcomponent(modules = [CreateModule::class])
interface CreateComponent {
    fun inject(view: CreateTodoActivity)
}

