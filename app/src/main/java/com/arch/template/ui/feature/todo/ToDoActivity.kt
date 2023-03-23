package com.arch.template.ui.feature.todo

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.arch.presentation.model.ToDoPresentation
import com.arch.presentation.viewmodels.todo.ToDoViewModel
import com.arch.template.R
import com.arch.template.base.BaseActivity
import com.arch.template.databinding.ActivityTodoBinding
import com.arch.template.ui.feature.todo.addtodo.AddToDoActivity
import com.arch.template.utils.hide
import com.arch.template.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ToDoActivity : BaseActivity<ActivityTodoBinding, ToDoViewModel>() {

    override val viewModel by viewModels<ToDoViewModel>()

    override fun getLayoutRes() = R.layout.activity_todo

    override fun initViewModel(viewModel: ToDoViewModel) {

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.rvTodo.adapter = toDoAdapter
        lifecycleScope.launchWhenStarted {
            viewModel.todoPageFlow.collect { todoPageFlow ->
                if (todoPageFlow.isNotEmpty()) {
                    binding.tvEmptyView.hide()
                    binding.rvTodo.show()
                    toDoAdapter.updateData(todoPageFlow)
                } else {
                    binding.tvEmptyView.show()
                    binding.rvTodo.hide()
                }
            }
        }

        viewModel.getToDoList()
        binding.tvHeader.setOnClickListener {
            //viewModel.tryError()
        }

        binding.fabAddTodo.setOnClickListener {
            gotoAddTodo()
        }
    }


    private fun gotoAddTodo() {
        startActivity(
            Intent(this@ToDoActivity, AddToDoActivity::class.java)
        )
    }

    private val toDoAdapter by lazy {
        ToDoAdapter(arrayListOf(), this::onEditAction)
    }

    private fun onEditAction(toDoPresentation: ToDoPresentation) {
        startActivity(
            Intent(this@ToDoActivity, AddToDoActivity::class.java).apply {
                putExtra("todo", toDoPresentation)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getToDoList()
    }
}