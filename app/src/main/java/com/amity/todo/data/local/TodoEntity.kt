package com.amity.todo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class for the Todo feature
// This class is responsible for defining the table and its columns
// It also provides a constructor to create an instance of the entity

@Entity
data class TodoEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)
