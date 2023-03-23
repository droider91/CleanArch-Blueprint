package com.arch.presentation.viewmodels.todo

import androidx.databinding.Observable
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.arch.entity.ToDoData
import com.arch.error.BaseError
import com.arch.errors.android.handler.IAndroidExceptionHandler
import com.arch.logger.AppLogger
import com.arch.permissions.android.IAndroidPermissionsController
import com.arch.presentation.model.ToDoPresentation
import com.arch.presentation.viewmodels.base.ObservableBaseViewModel
import com.arch.usecase.todo.GetToDoUseCase
import com.arch.utils.Either
import com.arch.utils.RequestManager
import com.arch.utils.Status.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val getToDoUseCase: GetToDoUseCase,
    exceptionHandler: IAndroidExceptionHandler,
    permissionHandler: IAndroidPermissionsController,
    logger: AppLogger,
) :
    ObservableBaseViewModel(exceptionHandler, permissionHandler, logger), Observable {


    private val _todoPageFlow: MutableSharedFlow<List<ToDoPresentation>> =
        MutableSharedFlow()

    val todoPageFlow: SharedFlow<List<ToDoPresentation>> = _todoPageFlow


    fun getToDoList() {
        viewModelScope.launch {
            exceptionHandler.handle {
                val toDoParams = GetToDoUseCase.ToDoParams(
                    PagingConfig(pageSize = 4)
                )
                object : RequestManager<List<ToDoData>>(params = toDoParams) {
                    override suspend fun createCall(): Either<BaseError, List<ToDoData>> {
                        return getToDoUseCase.execute(
                            params = toDoParams
                        )
                    }
                }.asFlow().collect {
                    when (it.status) {
                        SUCCESS -> {
                            _todoPageFlow.emit(it.data!!.map { dataCls ->
                                ToDoPresentation().restore(
                                    dataCls
                                )
                            })
                        }
                        LOADING -> {}
                        ERROR -> {

                        }
                    }
                }
                //request
            }.catch<Exception> {
                true
            }.finally {
            }.execute()
        }
    }
}