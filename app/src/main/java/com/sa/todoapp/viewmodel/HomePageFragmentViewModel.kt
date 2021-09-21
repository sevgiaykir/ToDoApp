package com.sa.todoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.repo.ToDoDAORepository

class HomePageFragmentViewModel(application: Application): AndroidViewModel(application) {
    var todoList=MutableLiveData<List<ToDoList>>()
    val tdaor=ToDoDAORepository(application)

    init{
        getToDoList()
        todoList=tdaor.getTasks()
    }

    fun getToDoList() {
        tdaor.getAllTasks()
    }

    fun search(searchWord:String) {
        tdaor.searchTask(searchWord)
    }

    fun delete(task:ToDoList) {
        tdaor.deleteTask(task)
    }
}