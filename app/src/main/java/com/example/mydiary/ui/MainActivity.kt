package com.example.mydiary.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.MainContract
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.models.TodoModel
import com.example.mydiary.presenter.MainPresenter
import com.example.mydiary.repository.TodoRepository
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null
    private var presenter: MainContract.Presenter? = null

    // private var adapter: TodoAdapter? = null
    // private var recyclerView: RecyclerView? = null
    @Inject
    lateinit var repository: TodoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        (applicationContext as MyDiaryApp).appComponent.inject(this)

        setPresenter(MainPresenter(this))
        val testlist = listOf(
            TodoModel(1, 234334L, 345254L, "name1", "descr1"),
            TodoModel(2, 324142L, 3433243L, "name2", "descri2")
        )
        Log.e("ACTIVITY", "!!!!!!!!!!!!!!!")
        repository.readJson()
        // repository.writeJson(testlist)
        repository.readJson()
        Log.e("ACTIVITY", "!!!!!!!!!!!!!!!")

    }

    override fun onDayClicked() {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}