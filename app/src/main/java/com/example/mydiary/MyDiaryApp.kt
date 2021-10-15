package com.example.mydiary

import android.app.Application
import com.example.mydiary.di.AppComponent
import com.example.mydiary.di.AppModule
import com.example.mydiary.di.DaggerAppComponent
import com.example.mydiary.repository.TodoRepository
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class MyDiaryApp : Application() {

    val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()

}