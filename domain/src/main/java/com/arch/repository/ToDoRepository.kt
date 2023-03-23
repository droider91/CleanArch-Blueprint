package com.arch.repository

import androidx.paging.PagingConfig
import com.arch.entity.AddToDoResponse
import com.arch.entity.ToDoData
import com.arch.error.BaseError
import com.arch.utils.Either

interface ToDoRepository {
    suspend fun getToDoData(pagingConfig: PagingConfig): Either<BaseError, List<ToDoData>>
    suspend fun addToDo(toDoData: ToDoData): Either<BaseError, AddToDoResponse>
    suspend fun updateToDo(toDoData: ToDoData): Either<BaseError, AddToDoResponse>
}