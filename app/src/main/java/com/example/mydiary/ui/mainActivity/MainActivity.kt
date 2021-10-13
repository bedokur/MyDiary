package com.example.mydiary.ui.mainActivity

import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.adapters.TodoAdapter
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.di.MainModule
import com.example.mydiary.models.TodoModel
import com.example.mydiary.ui.createActivity.CreateTodoActivity
import com.example.mydiary.ui.detailsActivity.DetailsActivity
import com.example.mydiary.ui.detailsActivity.Navigator
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), MainContract.View, Navigator {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var presenter: MainContract.Presenter
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

        val debug = binding?.calendarView?.date

        showOnCreateTodo(debug!!)


        binding?.calendarView?.setOnDateChangeListener { calView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            Log.d("MainActivity", "$year , $month , $dayOfMonth")
            presenter.showTodoItems(year, month+1, dayOfMonth) //+1 потому как calendarView отсчет месяцев ведет с нуля

        }

        binding?.floatingActionButton?.setOnClickListener{
            CreateTodoActivity.start(this)
        }
    }

    private fun showOnCreateTodo(debug: Long) {
        val fYear = SimpleDateFormat("yyyy", Locale.getDefault())
        val fMonth = SimpleDateFormat("MM", Locale.getDefault())
        val fDayOfMonth = SimpleDateFormat("dd", Locale.getDefault())

        presenter.showTodoItems(
            year = fYear.format(debug).toInt(),
            month = fMonth.format(debug).toInt(),
            dayOfMonth = fDayOfMonth.format(debug).toInt()
        )
    }

    override fun showTodoList(listForView: List<TodoModel>) {
        adapter?.submitList(listForView)
    }

    override fun showError(text: String?) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        presenter.onDestroy()
    }

    override fun navigate(item: TodoModel) {
        DetailsActivity.start(this, item)
    }
}