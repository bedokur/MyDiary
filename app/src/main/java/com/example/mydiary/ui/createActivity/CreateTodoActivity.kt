package com.example.mydiary.ui.createActivity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mydiary.MyDiaryApp
import com.example.mydiary.R
import com.example.mydiary.databinding.ActivityCreateBinding
import com.example.mydiary.di.CreateModule
import com.example.mydiary.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import javax.inject.Inject

class CreateTodoActivity @Inject constructor() : AppCompatActivity(), CreateContract.View {

    private var binding: ActivityCreateBinding? = null
    private var startHour: Int? = null
    private var startMinute: Int? = null

    @Inject
    lateinit var presenter: CreateContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        (applicationContext as MyDiaryApp).appComponent.plus(CreateModule(this)).inject(this)

        binding?.startTv?.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                null,
                Calendar.YEAR,
                Calendar.MONTH,
                Calendar.DAY_OF_MONTH
            )
            dpd.show()
            val dp = dpd.datePicker
            dp.minDate = System.currentTimeMillis()
            dpd.setOnDateSetListener { _, year, month, dayOfMonth ->
                presenter.peekStartDays(year, month, dayOfMonth)
                peekStartHours()
            }
        }

        binding?.finishTv?.setOnClickListener {
            if (startHour != null && startMinute != null) {
                TimePickerDialog(
                    this,
                    finishCallback,
                    startHour!!,
                    startMinute!!,
                    true
                ).show()
            }
        }

        binding?.nameInput?.setOnFocusChangeListener { view, b ->
            if (!b) {
                view.hideKeyboard()
            }
        }

        binding?.descriptionInput?.setOnFocusChangeListener { view, b ->
            if (!b) {
                view.hideKeyboard()
            }
        }

        binding?.saveTodo?.setOnClickListener {
            when {
                binding?.nameInput?.text.isNullOrBlank() -> {
                    binding?.nameInputLayout?.setErrorTextAppearance(R.style.error_appearance)
                    binding?.nameInputLayout?.error = "Введите название!"
                }
                binding?.descriptionInput?.text.isNullOrBlank() -> {
                    binding?.descriptionInputLayout?.setErrorTextAppearance(R.style.error_appearance)
                    binding?.descriptionInputLayout?.error = "Необходимо коротко описать дело."
                }
                else -> {
                    presenter.saveTodo(
                        binding?.nameInput?.text.toString(),
                        binding?.descriptionInput?.text.toString()
                    )
                    it.hideKeyboard()
                }
            }
        }
    }

    private fun peekStartHours() {
        val calendar = Calendar.getInstance()

        TimePickerDialog(
            this,
            startCallback,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    override fun showDateStart(dateStart: String) {
        binding?.startTv?.text = dateStart
    }

    override fun showDateFinish(dateFinish: String) {
        binding?.finishTv?.text = dateFinish
    }

    override fun showError(text: String) {
        Snackbar.make(this, binding?.saveTodo!!, text, Snackbar.LENGTH_LONG).show()
    }

    override fun finishActivity() {
        finish()
    }

    private val startCallback = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        startHour = hour
        startMinute = minute
        presenter.peekStartTime(hour, minute)
    }
    private val finishCallback = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        presenter.peekFinishTime(hour, minute)
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, CreateTodoActivity::class.java)
            activity.startActivity(intent)
        }
    }
}