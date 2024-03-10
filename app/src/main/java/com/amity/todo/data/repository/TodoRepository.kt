package com.amity.todo.data.repository

import android.util.Log
import com.amity.todo.data.local.TodoDao
import com.amity.todo.data.local.TodoEntity
import com.amity.todo.data.remote.TodoApi
import kotlinx.coroutines.flow.Flow

// Data repository for the Todo feature
// This class is responsible for fetching data from the API and storing it in the database
// It also provides methods to query the database

class TodoRepository (
    private val api: TodoApi,
    private val dao: TodoDao
) {
    suspend fun fetchAndStoreTodos() {
        val todos = api.getTodos().map { todo ->
            TodoEntity(
                id = todo.id,
                userId = todo.userId,
                title = todo.title,
                completed = todo.completed
            )
        }
        Log.d("TodoRepository", "fetchAndStoreTodos: ${todos.size}")
        dao.insertTodos(todos)
    }

    fun getTodosFromDatabase(): Flow<List<TodoEntity>?> {
        return dao.getTodos()
    }

    fun searchTodos(query: String): Flow<List<TodoEntity>> {
        return dao.searchTodos(query)
    }
}