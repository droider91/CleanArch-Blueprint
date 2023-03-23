package com.arch.template.ui.feature.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arch.presentation.model.ToDoPresentation
import com.arch.template.databinding.ItemTodoBinding

class ToDoAdapter(
    private val todoList: ArrayList<ToDoPresentation>,
    private val onEditAction: (todo: ToDoPresentation) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    fun updateData(updatedList: List<ToDoPresentation>) {
        todoList.clear()
        todoList.addAll(updatedList)
        notifyDataSetChanged()
    }

    private lateinit var binding: ItemTodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.bind(todo)
        binding.tvEdit.setOnClickListener {
            onEditAction(todo)
        }
    }

    override fun getItemCount(): Int = todoList.size
    class ToDoViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: ToDoPresentation) {
            binding.toDoPresentation = todo
        }
    }
}