package com.arch.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.arch.data.database.dao.base.BaseDao
import com.arch.data.entity.local.todo.ToDoEntity

@Dao
abstract class ToDoEntityDao:BaseDao<ToDoEntity>