package com.example.mydiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mydiary.MainContract
import com.example.mydiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null
    // private var adapter: TodoAdapter? = null
    // private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val dateStart = System.currentTimeMillis()
        val dateFinish = System.currentTimeMillis() + 100000L


    }

    override fun onDayClicked() {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        TODO("Not yet implemented")
    }
}