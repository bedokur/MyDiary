package com.example.mydiary.di

import com.example.mydiary.ui.createActivity.CreateTodoActivity
import com.example.mydiary.ui.mainActivity.MainActivity
import dagger.Subcomponent

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
