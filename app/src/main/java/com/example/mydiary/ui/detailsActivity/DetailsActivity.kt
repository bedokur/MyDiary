package com.example.mydiary.ui.detailsActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.databinding.ActivityDetailsBinding
import com.example.mydiary.models.TodoModel

class DetailsActivity : AppCompatActivity() {

    private var binding: ActivityDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        val extras = intent.extras

        binding?.name?.text = extras?.getString("name")
        binding?.description?.text = extras?.getString("description")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun start(activity: AppCompatActivity, item: TodoModel) {
            val intent = Intent(activity, DetailsActivity::class.java).apply {
                putExtra("name", item.name)
                putExtra("date", item.date_start)
                putExtra("date_finish", item.date_finish)
                putExtra("description", item.description)
            }
            activity.startActivity(intent)
        }
    }
}