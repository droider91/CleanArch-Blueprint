package com.arch.data.database.dao.base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arch.data.entity.local.todo.ToDoEntity

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(obj: List<T>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T?):Long


    @Query("SELECT * FROM todos")
    suspend fun getAllToDos(): List<ToDoEntity>


}
