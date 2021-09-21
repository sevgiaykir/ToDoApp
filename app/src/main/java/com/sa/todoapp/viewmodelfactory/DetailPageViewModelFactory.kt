package com.sa.todoapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sa.todoapp.viewmodel.DetailPageViewModel
import com.sa.todoapp.viewmodel.HomePageFragmentViewModel

class DetailPageViewModelFactory (private val application: Application)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailPageViewModel(application) as T
    }
}