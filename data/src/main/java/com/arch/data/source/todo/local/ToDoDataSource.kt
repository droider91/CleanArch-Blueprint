package com.arch.data.source.todo.local

import com.arch.data.entity.local.todo.ToDoEntity

interface ToDoDataSource {
    suspend fun getToDos(): List<ToDoEntity>
    suspend fun insertToDo(toDoEntity: ToDoEntity): Boolean
}