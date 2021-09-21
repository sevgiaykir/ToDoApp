package com.sa.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sa.todoapp.R
import com.sa.todoapp.databinding.FragmentRegistrationPageBinding
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.viewmodel.RegistrationPageViewModel
import com.sa.todoapp.viewmodelfactory.RegistrationPageViewModelFactory

class RegistrationPageFragment : Fragment() {

    private lateinit var design:FragmentRegistrationPageBinding
    private lateinit var viewModel:RegistrationPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design=DataBindingUtil.inflate(inflater,
            R.layout.fragment_registration_page, container, false)
        design.registrationFragment=this
        design.registerPageToolbarTitle="Yapılacak İş Kayıt"


        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:RegistrationPageViewModel by viewModels(){
            RegistrationPageViewModelFactory(requireActivity().application)
        }
        viewModel=tempViewModel

    }

    fun buttonRegisterClick(to_do:String) {
        val newTask=ToDoList(0,to_do)
        viewModel.register(newTask)
        Toast.makeText(requireContext(),"Kayıt eklendi.", Toast.LENGTH_SHORT).show()
    }
}