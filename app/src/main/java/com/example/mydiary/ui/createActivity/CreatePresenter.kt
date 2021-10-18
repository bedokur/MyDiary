package com.example.mydiary.ui.createActivity

import com.example.mydiary.models.TodoModel
import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.utils.longDateToInd
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreatePresenter(var view: CreateContract.View?, var repository: TodoRepository) :
    CreateContract.Presenter {

    private var todoDate: String? = null
    private var startTime: String? = null
    private var finishTime: String? = null
    private var currentList: List<TodoModel>? = null

    override fun peekStartDays(year: Int, month: Int, datOfMonth: Int) {
        todoDate = "$datOfMonth-${month + 1}-$year"
    }

    override fun peekStartTime(hour: Int, minute: Int) {
        startTime = " $hour:$minute"
        view?.showDateStart(todoDate + startTime)
    }

    override fun peekFinishTime(hour: Int, minute: Int) {
        finishTime = " $hour:$minute"
        if (todoDate != null && checkTime(startTime!!, finishTime!!)) {
            view?.showDateFinish(todoDate + finishTime)
        } else {
            view?.showError("Конечное время не может быть меньше начального!")
        }
    }

    override fun saveTodo(name: String, description: String) {
        val id = repository.calcHighestId() + 1
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val startDate = todoDate + startTime
        val finishDate = todoDate + finishTime
        val item = TodoModel(
            id = id,
            date_start = formatter.parse(startDate)!!.time,
            date_finish = formatter.parse(finishDate)!!.time,
            name = name,
            description = description
        )
        if (!checkCollisions(item)) {
            val list = listOf(item)
            repository.writeJson(list)
            view?.finishActivity()
        } else {
            view?.showError("У вас есть дело в это время! Постарайтесь перенести в другое время.")
        }
    }

    /**
     * функция для проверки пересечений с существующими делами, при пересечении нельзя запланировать
     * новое дело
     */
    private fun checkCollisions(item: TodoModel): Boolean {
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val filteredList = currentList?.filter {
            formatter.format(Date(item.date_start)) == formatter.format(Date(it.date_start))
        } as MutableList<TodoModel>

        filteredList.sortBy {
            it.date_start
        }
        if (filteredList.isEmpty()) {
            return false
        }
        if (longDateToInd(item.date_start) < longDateToInd(filteredList[0].date_start)) {
            if (longDateToInd(item.date_finish) <= longDateToInd(filteredList[0].date_start)) {
                return false
            }
        }
        if (longDateToInd(item.date_start) >= longDateToInd(filteredList.last().date_finish)) {
            return false
        }
        for (ind in 0 until filteredList.size) {
            if (longDateToInd(item.date_start) >= longDateToInd(filteredList[ind].date_finish)) {
                if (filteredList.getOrNull(ind + 1) != null) {
                    if (longDateToInd(item.date_finish) <= longDateToInd(filteredList[ind + 1].date_start)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun checkTime(startTime: String, finishTime: String): Boolean {
        val formatter = SimpleDateFormat(" HH:mm", Locale.getDefault())
        return formatter.parse(startTime)!!.time < formatter.parse(finishTime)!!.time
    }

    override fun onDestroy() {
        this.view = null
    }

    init {
        repository.getSingleListFromJson()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                currentList = it
            }, {
                Throwable(it.localizedMessage)
            })// сделал так чтобы находить самый большой id по актуальному списку,
        // хотя по факту - возможно лишний эммит
        //и еще для проверок пересечений дат
    }
}

