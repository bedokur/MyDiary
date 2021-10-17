package com.example.mydiary.di

import com.example.mydiary.di.module.CreateModule
import com.example.mydiary.di.module.DetailsModule
import com.example.mydiary.di.module.MainModule
import com.example.mydiary.ui.createActivity.CreateTodoActivity
import com.example.mydiary.ui.detailsActivity.DetailsActivity
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

@ActivityScope
@Subcomponent(modules = [DetailsModule::class])
interface  DetailsComponent{
    fun inject(view: DetailsActivity)
}