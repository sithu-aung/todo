package com.amity.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

// Database for the Todo feature
// This class is responsible for defining the database and providing access to the DAO
// It also defines the version of the database

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract val dao: TodoDao
}