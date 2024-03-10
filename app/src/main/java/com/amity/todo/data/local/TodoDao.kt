package com.amity.todo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Data access object for the Todo feature

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)

    @Query("SELECT * FROM todoentity")
    fun getTodos(): Flow<List<TodoEntity>>

    @Query("DELETE FROM todoentity")
    suspend fun clearAll()

    @Query("SELECT * FROM todoentity WHERE title LIKE '%' || :query || '%'")
    fun searchTodos(query: String): Flow<List<TodoEntity>>
}