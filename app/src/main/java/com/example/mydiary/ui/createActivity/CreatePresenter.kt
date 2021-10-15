package com.example.mydiary.ui.createActivity

import com.example.mydiary.models.TodoModel
import com.example.mydiary.repository.TodoRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Locale

class CreatePresenter(var view: CreateContract.View?, var repository: TodoRepository) :
    CreateContract.Presenter {

    private var todoDate: String? = null
    private var startTime: String? = null
    private var finishTime: String? = null

    override fun peekStartDays(year: Int, month: Int, datOfMonth: Int) {
        todoDate = "$datOfMonth-${month+1}-$year"
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
    }

    override fun saveTodo(name: String, description: String) {
        val id = repository.calcHighestId() + 1
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val startDate = todoDate+startTime
        val finishDate = todoDate+finishTime
        val item = TodoModel(
            id = id,
            date_start = formatter.parse(startDate)!!.time,
            date_finish = formatter.parse(finishDate)!!.time,
            name = name,
            description = description
        )
        val list = listOf(item)
        repository.writeJson(list)
    }

    override fun onDestroy() {
        this.view = null
    }

    init {
        repository.getSingleListFromJson()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe()// сделал так чтобы находить самый большой id по актуальному списку,
                        // хотя по факту - лишний эмит
    }

}