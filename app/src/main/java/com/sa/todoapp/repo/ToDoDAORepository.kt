package com.sa.todoapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.room.ToDoDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToDoDAORepository(var application: Application) {
    private val db:ToDoDB
    private val todoList: MutableLiveData<List<ToDoList>>

    init {
        db= ToDoDB.databaseAccess(application)!!
        todoList= MutableLiveData()
    }

    fun getTasks():MutableLiveData<List<ToDoList>> {
        return todoList
    }

    fun getAllTasks() {
        val job:Job= CoroutineScope(Dispatchers.Main).launch {
            todoList.value= db.toDoDao().allTasks()
        }
    }

    fun searchTask(searchWord:String) {
        val job:Job= CoroutineScope(Dispatchers.Main).launch {
            todoList.value= db.toDoDao().searchTask(searchWord)
        }
    }

    fun deleteTask(task:ToDoList) {
        val job:Job= CoroutineScope(Dispatchers.Main).launch {
            db.toDoDao().deleteTask(task)
            getAllTasks()
        }
    }

    fun addTask(task:ToDoList) {
        val job:Job= CoroutineScope(Dispatchers.Main).launch {
            db.toDoDao().addTask(task)
        }
    }

    fun updateTask(task:ToDoList) {
        val job:Job= CoroutineScope(Dispatchers.Main).launch {
            db.toDoDao().updateTask(task)
        }
    }
}