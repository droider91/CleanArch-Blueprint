package com.arch.presentation.viewmodels.todo.addtodo

import androidx.databinding.Observable
import androidx.lifecycle.viewModelScope
import com.arch.entity.AddToDoResponse
import com.arch.entity.ToDoData
import com.arch.error.BaseError
import com.arch.errors.android.handler.IAndroidExceptionHandler
import com.arch.logger.AppLogger
import com.arch.permissions.android.IAndroidPermissionsController
import com.arch.presentation.model.AddToDoPresentation
import com.arch.presentation.viewmodels.base.ObservableBaseViewModel
import com.arch.usecase.todo.AddToDoUseCase
import com.arch.usecase.todo.UpdateToDoUseCase
import com.arch.utils.Either
import com.arch.utils.RequestManager
import com.arch.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val addToDoUseCase: AddToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    exceptionHandler: IAndroidExceptionHandler,
    permissionHandler: IAndroidPermissionsController,
    logger: AppLogger,
) :
    ObservableBaseViewModel(exceptionHandler, permissionHandler, logger), Observable {


    private val _addToDoPageFlow: MutableSharedFlow<AddToDoResponse> =
        MutableSharedFlow()

    val addToDoPageFlow: MutableSharedFlow<AddToDoResponse> = _addToDoPageFlow


    fun addToDoItem(title: String, subTitle: String) {
        viewModelScope.launch {
            exceptionHandler.handle {
                val toDoParams =
                    AddToDoUseCase.ToDoParams(ToDoData(id = 0, title = title, subTitle = subTitle))
                object : RequestManager<AddToDoResponse>(params = toDoParams) {
                    override suspend fun createCall(): Either<BaseError, AddToDoResponse> {
                        return addToDoUseCase.execute(
                            params = toDoParams
                        )
                    }
                }.asFlow().collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _addToDoPageFlow.emit(
                                AddToDoPresentation().restore(it.data!!)
                            )
                        }
                    }
                }
                //request
            }.catch<Exception> {
                false
            }.execute()
        }
    }

    fun updateToDoItem(title: String, subTitle: String) {
        viewModelScope.launch {
            exceptionHandler.handle {
                val toDoParams =
                    UpdateToDoUseCase.ToDoParams(
                        ToDoData(
                            id = 0,
                            title = title,
                            subTitle = subTitle
                        )
                    )
                object : RequestManager<AddToDoResponse>(params = toDoParams) {
                    override suspend fun createCall(): Either<BaseError, AddToDoResponse> {
                        return updateToDoUseCase.execute(
                            params = toDoParams
                        )
                    }
                }.asFlow().collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _addToDoPageFlow.emit(
                                AddToDoPresentation().restore(it.data!!)
                            )
                        }
                    }
                }
                //request
            }.catch<Exception> {
                false
            }.execute()
        }
    }

}