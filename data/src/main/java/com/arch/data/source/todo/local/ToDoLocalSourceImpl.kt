package com.arch.data.source.todo.local

import com.arch.data.database.dao.ToDoEntityDao
import com.arch.data.entity.local.todo.ToDoEntity
import javax.inject.Inject

class ToDoLocalSourceImpl @Inject constructor(private val toDoEntityDao: ToDoEntityDao) :
    ToDoDataSource {
    override suspend fun getToDos(): List<ToDoEntity> {
        return toDoEntityDao.getAllToDos()
    }

    override suspend fun insertToDo(toDoEntity: ToDoEntity): Boolean {
        return toDoEntityDao.insert(toDoEntity) > 0
    }
}