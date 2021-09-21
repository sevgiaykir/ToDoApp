package com.sa.todoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.repo.ToDoDAORepository

class RegistrationPageViewModel(application: Application): AndroidViewModel(application) {

    val tdaor= ToDoDAORepository(application)

    fun register(task:ToDoList) {
        tdaor.addTask(task)
    }

}