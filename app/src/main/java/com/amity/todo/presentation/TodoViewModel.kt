package com.amity.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amity.todo.data.repository.TodoRepository
import com.amity.todo.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// ViewModel for the Todo feature
// This class is responsible for providing data to the UI and handling user interactions
// It also provides methods to query the database
// It also provides methods to fetch and store data from the API

class TodoViewModel (
    private val repository: TodoRepository
) : ViewModel() {
    var todos: Flow<List<TodoEntity>?> = repository.getTodosFromDatabase()
    val isLoading = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            fetchAndStoreTodos()
        }
    }

    private suspend fun fetchAndStoreTodos() {
        isLoading.value = true
        repository.fetchAndStoreTodos()
        isLoading.value = false
    }

    fun searchTodos(query: String) {
        isLoading.value = true
        todos = repository.searchTodos(query)
        isLoading.value = false
    }
}