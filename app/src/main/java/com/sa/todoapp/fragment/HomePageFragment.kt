package com.sa.todoapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sa.todoapp.R
import com.sa.todoapp.adapter.ToDoListAdapter
import com.sa.todoapp.databinding.FragmentHomePageBinding
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.viewmodel.HomePageFragmentViewModel
import com.sa.todoapp.viewmodelfactory.HomePageFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_home_page.view.*

class HomePageFragment : Fragment(),SearchView.OnQueryTextListener {

    private lateinit var design:FragmentHomePageBinding
    private lateinit var adapter: ToDoListAdapter
    private lateinit var viewModel: HomePageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design=DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)
        design.homePageFragment=this

        design.homePageToolbarTitle="Yapılacaklar"
        (activity as AppCompatActivity).setSupportActionBar(design.toolbarHomepage)

        viewModel.todoList.observe(viewLifecycleOwner, { toDoList ->
            adapter= ToDoListAdapter(requireContext(),toDoList,viewModel)
            design.toDoAdapter=adapter
        })

        return design.root
    }

    fun fabClick(v:View) {
        Navigation.findNavController(v).navigate(R.id.transition_homePage_to_registrationPage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:HomePageFragmentViewModel by viewModels() {
            HomePageFragmentViewModelFactory(requireActivity().application)
        }
        viewModel=tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)

        val item=menu.findItem(R.id.action_search)
        val searchView=item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }
/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_search -> {
                Toast.makeText(requireContext(),"Ara Tıklandı",Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return  super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
*/
    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Arama: tuşuna basılınca",query)
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Arama: harf girdikçe",newText)
        viewModel.search(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.getToDoList()
    }
}