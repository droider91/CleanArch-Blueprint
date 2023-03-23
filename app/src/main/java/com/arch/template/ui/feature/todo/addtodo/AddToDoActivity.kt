package com.arch.template.ui.feature.todo.addtodo

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.arch.presentation.model.ToDoPresentation
import com.arch.presentation.viewmodels.todo.addtodo.AddToDoViewModel
import com.arch.template.R
import com.arch.template.base.BaseActivity
import com.arch.template.databinding.ActivityAddTodoBinding
import com.arch.template.utils.hide
import com.arch.template.utils.hideKeyboard
import com.arch.template.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class AddToDoActivity : BaseActivity<ActivityAddTodoBinding, AddToDoViewModel>() {

    override val viewModel by viewModels<AddToDoViewModel>()

    override fun getLayoutRes() = R.layout.activity_add_todo

    override fun initViewModel(viewModel: AddToDoViewModel) {

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val extra = intent.getParcelableExtra<ToDoPresentation>("todo")
        extra?.let {
            binding.apply {
                etTitle.setText(extra.title)
                etSubTitle.setText(extra.subTitle)
                btnSaveToDo.hide()
                btnUpdateToDo.show()
            }
        }


        lifecycleScope.launchWhenStarted {
            viewModel.addToDoPageFlow.collect {
                if (it.isAdded) {
                    this@AddToDoActivity.finish()
                }
            }
        }


        binding.ivBack.setOnClickListener {
            this@AddToDoActivity.finish()
        }


        binding.btnUpdateToDo.setOnClickListener {
            binding.btnSaveToDo.hideKeyboard()
            viewModel.addToDoItem(
                binding.etTitle.text.toString(),
                binding.etSubTitle.text.toString()
            )
        }



        binding.btnSaveToDo.setOnClickListener {
            binding.btnSaveToDo.hideKeyboard()
            viewModel.addToDoItem(
                binding.etTitle.text.toString(),
                binding.etSubTitle.text.toString()
            )

        }
        binding.etSubTitle.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.addToDoItem(
                    binding.etTitle.text.toString(),
                    binding.etSubTitle.text.toString()
                )
                return@OnEditorActionListener true
            }
            false
        })
    }

}