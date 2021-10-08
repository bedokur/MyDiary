package com.example.mydiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.di.MainModule
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null
    @Inject lateinit var presenter: MainContract.Presenter
    // private var adapter: TodoAdapter? = null
    // private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        (applicationContext as MyDiaryApp).appComponent.plus(MainModule(this)).inject(this)


        // setPresenter(MainPresenter(this))
        presenter.showTodoItems()

    }

    override fun showTodoList() {
        TODO("Not yet implemented")
    }
}