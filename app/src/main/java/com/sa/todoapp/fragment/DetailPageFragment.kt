package com.sa.todoapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sa.todoapp.R
import com.sa.todoapp.databinding.FragmentDetailPageBinding
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.viewmodel.DetailPageViewModel
import com.sa.todoapp.viewmodelfactory.DetailPageViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail_page.view.*

class DetailPageFragment : Fragment() {

    private lateinit var design:FragmentDetailPageBinding
    private lateinit var viewModel:DetailPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design=DataBindingUtil.inflate(inflater,R.layout.fragment_detail_page, container, false)
        design.detailFragment=this
        design.detailPageToolbarTitle="Yapılacak İş Detay"

        val bundle:DetailPageFragmentArgs by navArgs()
        val comingObject=bundle.toDoObject

        design.toToObject=comingObject

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailPageViewModel by viewModels(){
            DetailPageViewModelFactory(requireActivity().application)
        }
        viewModel=tempViewModel
    }

    fun buttonUpdateClick(to_do_id:Int, to_do:String) {
        val updateTask=ToDoList(to_do_id,to_do)
        viewModel.update(updateTask)
        Toast.makeText(requireContext(),"Kayıt güncellendi.", Toast.LENGTH_SHORT).show()
    }

}