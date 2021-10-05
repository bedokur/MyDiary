package com.example.mydiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private var binging: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging?.root)
    }
}