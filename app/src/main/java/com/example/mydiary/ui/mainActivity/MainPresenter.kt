package com.example.mydiary.ui.mainActivity

import com.example.mydiary.models.TodoModel
import com.example.mydiary.repository.TodoRepository
import com.example.mydiary.utils.Utils
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MainPresenter(var view: MainContract.View?, var repository: TodoRepository) :
    MainContract.Presenter {

    @Inject lateinit var utils: Utils

    private var todoList = emptyList<TodoModel>()

    private fun getTodoList() {
        Single.just(repository.readJson()) //это может быть бесполезно так как сама функция чтения пока в главном потоке
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                todoList = it.asList()
            }, {
                view?.showError(it.localizedMessage)
            })
    }

    /**
     * month +1 потому что "месяц" в календаре начинается с 0
     */
    override fun showTodoItems(year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth-${month}-$year"
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val dayMatchedList = todoList.filter {
            formatter.format(it.date_start) == selectedDate
        } as MutableList<TodoModel>

        var list = mutableListOf<TodoModel>()

        dayMatchedList.forEach {
            list = generateNewModelsForActivity(it)
        }
        view?.showTodoList(list)
    }

    private fun generateNewModelsForActivity(model: TodoModel): MutableList<TodoModel> {
        val newModel = utils.roundModelsDate(model)
        val itemsList = mutableListOf<TodoModel>()

        var i = newModel.date_start
        val i2 = newModel.date_finish
        val i3 = (i2 - i) / 3600000L

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
    init {
        getTodoList()
    }
    override fun onDestroy() {
        this.view = null
    }
}
