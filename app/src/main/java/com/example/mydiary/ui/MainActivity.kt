package com.example.mydiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mydiary.MainContract
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null
    private var presenter: MainContract.Presenter? = null
    // private var adapter: TodoAdapter? = null
    // private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setPresenter(MainPresenter(this))

    }

    override fun onDayClicked() {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}