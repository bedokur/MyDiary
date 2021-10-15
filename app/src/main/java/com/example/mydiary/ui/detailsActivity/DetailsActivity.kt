package com.example.mydiary.ui.detailsActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.databinding.ActivityDetailsBinding
import com.example.mydiary.models.TodoModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailsActivity : AppCompatActivity() {

    private var binding: ActivityDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        val extras = intent.extras!!

        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val start_date = formatter.format(Date(extras.getLong("date_start")))
        val finish_date = formatter.format(Date(extras.getLong("date_finish")))
        val representativeDate = "$start_date - $finish_date"

        binding?.date?.text = representativeDate
        binding?.name?.text = extras.getString("name")
        binding?.description?.text = extras.getString("description")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun start(activity: AppCompatActivity, item: TodoModel) {
            val intent = Intent(activity, DetailsActivity::class.java).apply {
                putExtra("name", item.name)
                putExtra("date_start", item.date_start)
                putExtra("date_finish", item.date_finish)
                putExtra("description", item.description)
            }
            activity.startActivity(intent)
        }
    }
}