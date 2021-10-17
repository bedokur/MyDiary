package com.example.mydiary.ui.detailsActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.databinding.ActivityDetailsBinding
import com.example.mydiary.di.module.DetailsModule
import com.example.mydiary.models.TodoModel
import com.example.mydiary.utils.formatToDate
import com.example.mydiary.utils.formatToHours
import javax.inject.Inject

class DetailsActivity @Inject constructor() : AppCompatActivity(), DetailsContract.View {

    private var binding: ActivityDetailsBinding? = null

    @Inject
    lateinit var presenter: DetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        (applicationContext as MyDiaryApp).appComponent.plus(DetailsModule(this)).inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        val extras = intent.extras
        if (extras != null)
            presenter.getTodoDetails(extras.getInt("itemID"))
    }

    override fun showDetails(item: TodoModel?) {
        if (item != null) {
            binding?.name?.text = item.name
            binding?.date?.text = formatToDate(item.date_start)
            binding?.startHour?.text = formatToHours(item.date_start)
            binding?.finishHour?.text = formatToHours(item.date_finish)
            binding?.description?.text = item.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        binding = null
    }

    companion object {
        fun start(activity: AppCompatActivity, itemID: Int) {
            val intent = Intent(activity, DetailsActivity::class.java).apply {
                putExtra("itemID", itemID)
            }
            activity.startActivity(intent)
        }
    }
}