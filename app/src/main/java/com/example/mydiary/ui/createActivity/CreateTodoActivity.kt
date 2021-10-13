package com.example.mydiary.ui.createActivity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.databinding.ActivityCreateBinding
import java.util.Calendar

class CreateTodoActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private var binding: ActivityCreateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        binding?.startTv?.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                this,
                Calendar.YEAR,
                Calendar.MONTH,
                Calendar.DAY_OF_MONTH
            )
            val dp = dpd.datePicker
            dp.minDate = System.currentTimeMillis()
            dpd.show()
        }
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, CreateTodoActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        Toast.makeText(this, "$p1 , $p2 , $p3", Toast.LENGTH_SHORT).show()
    }
}
