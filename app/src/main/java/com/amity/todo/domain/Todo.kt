package com.amity.todo.domain

// Domain class for the Todo feature
// This class is responsible for defining the data model
// It also provides a constructor to create an instance of the domain class

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
