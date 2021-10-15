package com.example.mydiary.repository

import android.content.Context
import android.util.Log
import com.example.mydiary.models.TodoModel
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class TodoRepository(private val context: Context) {

    private var currentList = arrayOf<TodoModel>()
    private var highestId: Int? = null

    fun readJson(): Array<TodoModel> {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            return currentList
        }
        val o = file.readText()
        currentList = Gson().fromJson(o, Array<TodoModel>::class.java)
        return currentList
    }

    fun writeJson(todoItemList: List<TodoModel>) {
        val array = JSONArray()
        currentList += todoItemList
        currentList.forEach {
            try {
                val ob = JSONObject()
                ob.put("id", it.id)
                ob.put("date_start", it.date_start)
                ob.put("date_finish", it.date_finish)
                ob.put("name", it.name)
                ob.put("description", it.description)
                array.put(ob)
                Log.i("", "")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val jsonFile = File(context.filesDir, fileName)
        val output = BufferedWriter(FileWriter(jsonFile))
        output.write(array.toString())
        output.close()
    }

    fun calcHighestId(): Int {
        highestId = currentList.maxOf {
            it.id
        }
        return highestId as Int
    }

    companion object {
        const val fileName = "data.json"
    }
}