package com.example.mydiary.baseinterfaces

import android.util.Log

interface BasePresenter {
    fun onDestroy(){
        Log.d("!!!!!!!!!!!!!!!!!!!!!!!","this worked")
    }
}