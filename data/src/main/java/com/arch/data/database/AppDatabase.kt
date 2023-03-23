package com.arch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arch.data.database.dao.ToDoEntityDao
import com.arch.data.database.dao.UserEntityDao
import com.arch.data.entity.local.UserEntity
import com.arch.data.entity.local.todo.ToDoEntity

@Database(
    entities = [
        UserEntity::class,
        ToDoEntity::class
    ],
    version = DatabaseProperties.DATABASE_VERSION,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserEntityDao
    abstract fun toDoDao(): ToDoEntityDao
}