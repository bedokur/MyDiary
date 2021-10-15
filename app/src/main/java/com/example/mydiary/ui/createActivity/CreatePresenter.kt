package com.example.mydiary.ui.createActivity

import com.example.mydiary.models.TodoModel
import com.example.mydiary.repository.TodoRepository
import java.text.SimpleDateFormat
import java.util.Locale

class CreatePresenter(var view: CreateContract.View?, var repository: TodoRepository) :
    CreateContract.Presenter {

    private var todoDate: String? = null
    private var startTime: String? = null
    private var finishTime: String? = null

    override fun peekStartDays(year: Int, month: Int, datOfMonth: Int) {
        todoDate = "$datOfMonth-$month-$year"
    }

    override fun peekStartTime(hour: Int, minute: Int) {
        startTime = " $hour:$minute"
        view?.showDateStart(todoDate + startTime)
    }

    override fun peekFinishTime(hour: Int, minute: Int) {
        finishTime = " $hour:$minute"
        if (todoDate != null) {
            view?.showDateFinish(todoDate + finishTime)
        }
        view?.showError("Выберите начальную дату!")
    }

    override fun saveTodo(name: String, description: String) {
        val id = repository.calcHighestId() + 1
        val formatter = SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault())
        val item = TodoModel(
            id = id,
            date_start = formatter?.parse(todoDate + startTime).time,
            date_finish = formatter.parse(todoDate + finishTime).time,
            name = name,
            description = description
        )
        val list = listOf(item)
        repository.writeJson(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
    }
}