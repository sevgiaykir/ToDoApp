package com.sa.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sa.todoapp.R
import com.sa.todoapp.databinding.CardDesignBinding
import com.sa.todoapp.entity.ToDoList
import com.sa.todoapp.fragment.HomePageFragmentDirections
import com.sa.todoapp.viewmodel.HomePageFragmentViewModel

class ToDoListAdapter(var mContext: Context, var toDoList:List<ToDoList>, var viewModel: HomePageFragmentViewModel)
    :RecyclerView.Adapter<ToDoListAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(cardDesignBinding: CardDesignBinding)
        : RecyclerView.ViewHolder(cardDesignBinding.root) {
        var cardDesignBinding: CardDesignBinding

        init {
            this.cardDesignBinding=cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = CardDesignBinding.inflate(layoutInflater,parent,false)
        return CardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val todo=toDoList.get(position)
        val t=holder.cardDesignBinding

        t.toDoObject=todo

        t.cardViewLine.setOnClickListener {
            val transition=HomePageFragmentDirections.transitionHomePageToDetailPage(todo)
            Navigation.findNavController(it).navigate(transition)
        }

        t.imageViewDelete.setOnClickListener {
            Snackbar.make(it,"${todo.todo_task} kaydı silinsin mi?",Snackbar.LENGTH_SHORT)
                .setAction("Evet") {
                    Snackbar.make(it,"Kayıt silindi.",Snackbar.LENGTH_SHORT).show()
                    viewModel.delete(todo)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return  toDoList.size
    }
}