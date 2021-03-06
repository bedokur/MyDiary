package com.example.mydiary.ui.mainActivity

import com.example.mydiary.models.TodoModel
import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.utils.Utils
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MainPresenter @Inject constructor(
    var view: MainContract.View?,
    var repository: TodoRepository,
    var utils: Utils
) :
    MainContract.Presenter {

    private var todoList = emptyList<TodoModel>()

    private fun getTodoList() {

        repository.getSingleListFromJson()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                todoList = it
            }, {
                view?.showError(it.localizedMessage)
            })
    }

    override fun showTodoItems(year: Int, month: Int, dayOfMonth: Int) {
        val mDayOfMonth: String = if (dayOfMonth.toString().length == 1) {
            "0$dayOfMonth"
        } else {
            "$dayOfMonth"
        }

        val selectedDate = "$mDayOfMonth-${month}-$year"
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val list = mutableListOf<TodoModel>()

        val dayMatchedList = todoList.filter {
            formatter.format(it.date_start) == selectedDate
        } as MutableList<TodoModel>

        dayMatchedList.sortBy {
            it.date_finish
        }

        dayMatchedList.forEach {
            list += generateNewModelsForActivity(it)
        }
        view?.showTodoList(list)
    }

    private fun generateNewModelsForActivity(model: TodoModel): MutableList<TodoModel> {
        val newModel = utils.roundModelsDate(model)
        val itemsList = mutableListOf<TodoModel>()

        var i = newModel.date_start
        val i2 = newModel.date_finish
        val i3 = (i2 - i) / 3600000L //число - количество экземпляров 1 дела,
        // но с расчетом округленного времени(12:00 - 13:00 и т.д.)

        for (mtp in 0 until i3.toInt()) {
            if (i >= i2) {
                val item = TodoModel(
                    id = model.id,
                    date_start = i,
                    date_finish = i2,
                    name = model.name,
                    description = model.description
                )
                itemsList.add(item)

                return itemsList
            }

            val item = TodoModel(
                id = model.id,
                date_start = i,
                date_finish = i + 3600000L,
                name = model.name,
                description = model.description
            )
            i += 3600000L
            itemsList.add(item)
        }

        return itemsList
    }

    override fun onResume() {
        getTodoList()
    }

    override fun onDestroy() {
        this.view = null
    }
}
