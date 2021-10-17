package com.example.mydiary.di

import com.example.mydiary.di.module.CreateModule
import com.example.mydiary.di.module.DetailsModule
import com.example.mydiary.di.module.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun plus(mainModule: MainModule): MainComponent
    fun plus(createModule: CreateModule): CreateComponent
    fun plus(detailsModule: DetailsModule): DetailsComponent
}

