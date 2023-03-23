package com.arch.data.entity.local.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String = "",
    val subTitle: String = "",
)
