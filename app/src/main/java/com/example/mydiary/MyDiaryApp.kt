package com.example.mydiary

import android.app.Application
import com.example.mydiary.di.AppComponent
import com.example.mydiary.di.AppModule
import com.example.mydiary.di.DaggerAppComponent

class MyDiaryApp : Application() {

    val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()
}