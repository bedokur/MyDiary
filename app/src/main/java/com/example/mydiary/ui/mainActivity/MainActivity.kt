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
import com.example.mydiary.di.module.MainModule
import com.example.mydiary.models.TodoModel
import com.example.mydiary.ui.createActivity.CreateTodoActivity
import com.example.mydiary.ui.detailsActivity.DetailsActivity
import com.example.mydiary.ui.detailsActivity.Navigator
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), MainContract.View, Navigator {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private var binding: ActivityMainBinding? = null
    private var adapter: TodoAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var cDate: Long? = null

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
            Log.d("MainActivity", "$year , $month , $dayOfMonth")
            presenter.showTodoItems(
                year,
                month + 1,
                dayOfMonth
            ) //+1 потому как calendarView отсчет месяцев ведет с нуля

        }

        binding?.floatingActionButton?.setOnClickListener {
            CreateTodoActivity.start(this)
        }
        cDate = binding?.calendarView?.date
        Toast.makeText(this, "toasted on created", Toast.LENGTH_SHORT).show()

    }

    private fun showOnResumeTodo(cDate: Long) {
        val fYear = SimpleDateFormat("yyyy", Locale.getDefault())
        val fMonth = SimpleDateFormat("MM", Locale.getDefault())
        val fDayOfMonth = SimpleDateFormat("dd", Locale.getDefault())

        presenter.showTodoItems(
            year = fYear.format(cDate).toInt(),
            month = fMonth.format(cDate).toInt(),
            dayOfMonth = fDayOfMonth.format(cDate).toInt()
        )
    }

    override fun showTodoList(listForView: List<TodoModel>) {
        Log.wtf("showTodoList", "$listForView")
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
        showOnResumeTodo(cDate!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        this.binding = null
    }
}