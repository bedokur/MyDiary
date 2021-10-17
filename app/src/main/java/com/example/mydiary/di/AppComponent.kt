package com.example.mydiary.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun plus(mainModule: MainModule): MainComponent
    fun plus(createModule: CreateModule): CreateComponent
}

