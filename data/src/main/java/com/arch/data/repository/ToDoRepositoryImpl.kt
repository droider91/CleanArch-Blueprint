package com.arch.data.repository

import android.util.Log
import androidx.paging.PagingConfig
import com.arch.data.entity.local.todo.ToDoEntity
import com.arch.data.source.todo.local.ToDoDataSource
import com.arch.entity.AddToDoResponse
import com.arch.entity.Error
import com.arch.entity.ToDoData
import com.arch.error.BaseError
import com.arch.error.NetworkError
import com.arch.repository.ToDoRepository
import com.arch.utils.Either
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoDataSource: ToDoDataSource,
) : ToDoRepository {

    override suspend fun getToDoData(pagingConfig: PagingConfig): Either<BaseError, List<ToDoData>> {
        val localToDos = toDoDataSource.getToDos()
        return localToDos.let {
            Either.Right(it.map { toDoEntity ->
                ToDoData(
                    toDoEntity.id.toInt(),
                    toDoEntity.title,
                    toDoEntity.subTitle
                )
            })
        }
    }

    override suspend fun addToDo(toDoData: ToDoData): Either<BaseError, AddToDoResponse> {
        val flag = toDoDataSource.insertToDo(
            ToDoEntity(
                0,
                title = toDoData.title,
                subTitle = toDoData.subTitle
            )
        )
        return if (flag) {
            Either.Right(AddToDoResponse(flag))
        } else {
            Either.Left(
                NetworkError(
                    Error("", "10000", ""),
                    "Note not saved",
                    403
                )
            )
        }
    }

    override suspend fun updateToDo(toDoData: ToDoData): Either<BaseError, AddToDoResponse> {
        val flag = toDoDataSource.insertToDo(
            ToDoEntity(
                0,
                title = toDoData.title,
                subTitle = toDoData.subTitle
            )
        )
        return if (flag) {
            Either.Right(AddToDoResponse(flag))
        } else {
            Either.Left(
                NetworkError(
                    Error("", "10000", ""),
                    "Note not saved",
                    403
                )
            )
        }
    }
}