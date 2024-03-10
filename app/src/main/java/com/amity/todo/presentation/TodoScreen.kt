package com.amity.todo.presentation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// Todo screen for the Todo feature
// This composable function is responsible for displaying the todo screen
// It takes a TodoViewModel as a parameter and displays the list of todos
// It also provides a search view to search for todos

@Composable
fun TodoScreen(
    viewModel: TodoViewModel = viewModel()
) {
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    var search by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 12.dp)) {
        Column {
            CustomSearchView(search = search, onValueChange = {
                search = it
                viewModel.searchTodos(search)
            })
            when {
                isLoading -> {
                    // Display a loading indicator
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }
                todos.isNullOrEmpty()  -> {
                    // Display a "No data" message
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = "No data")
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemsIndexed(todos!!) { index, todo ->
                            TodoItem(todo = todo)
                        }
                    }
                }
            }
        }
    }
}