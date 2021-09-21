package com.sa.todoapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sa.todoapp.viewmodel.RegistrationPageViewModel

class RegistrationPageViewModelFactory (private val application: Application)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrationPageViewModel(application) as T
    }
}