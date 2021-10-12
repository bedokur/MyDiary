package com.example.mydiary.di

import com.example.mydiary.ui.mainActivity.MainActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton



@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun plus (mainModule: MainModule) : MainComponent
}

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject (view : MainActivity)
}

