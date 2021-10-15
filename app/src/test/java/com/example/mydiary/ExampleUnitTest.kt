package com.example.mydiary

import com.example.mydiary.models.TodoModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testMax() {
        var todoList = listOf<TodoModel>()
        for(i in 1..10){
            val item =TodoModel(
                i,
                24324L,
                2341234L,
                "name$i",
                "descr$i"
            )
            todoList += item
        }
        val out = todoList.maxOf {
            it.id
        }
        println(out)
    }
}