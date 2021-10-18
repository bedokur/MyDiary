package com.example.mydiary.ui.mainActivity

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.adapters.TodoAdapter
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.di.module.MainModule
import com.example.mydiary.models.TodoModel
import com.example.mydiary.ui.createActivity.CreateTodoActivity
import com.example.mydiary.ui.detailsActivity.DetailsActivity
import com.example.mydiary.ui.detailsActivity.Navigator
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), MainContract.View, Navigator {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private var binding: ActivityMainBinding? = null
    private var adapter: TodoAdapter? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        (applicationContext as MyDiaryApp).appComponent.plus(MainModule(this)).inject(this)

        adapter = TodoAdapter(this)
        recyclerView = binding?.todoRv
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

        binding?.calendarView?.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->

            presenter.showTodoItems(
                year,
                month + 1,
                dayOfMonth
            )
            //+1 потому как calendarView отсчет месяцев ведет с нуля

        }

        binding?.floatingActionButton?.setOnClickListener {
            CreateTodoActivity.start(this)
        }
    }

    override fun showTodoList(listForView: List<TodoModel>) {
        adapter?.submitList(listForView)
    }

    override fun showError(text: String?) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    override fun navigate(itemID: Int) {
        DetailsActivity.start(this, itemID)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        this.binding = null
    }
}