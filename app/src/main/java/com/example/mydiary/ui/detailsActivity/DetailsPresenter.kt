package com.example.mydiary.ui.detailsActivity

import com.example.mydiary.repository.TodoRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsPresenter(var view: DetailsContract.View?, val repository: TodoRepository) :
    DetailsContract.Presenter {

    override fun getTodoDetails(id: Int) {
        repository.getSingleListFromJson()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                val item = list.find {
                    it.id == id
                }
                view?.showDetails(item)
            }, {

            })
    }

    override fun onDestroy() {
        this.view = null
    }
}