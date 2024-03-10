package com.amity.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.amity.todo.data.repository.TodoRepository
import com.amity.todo.data.local.TodoDatabase
import com.amity.todo.data.remote.TodoApi
import com.amity.todo.presentation.TodoScreen
import com.amity.todo.presentation.TodoViewModel
import com.amity.todo.ui.theme.TodoTheme
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Main activity for the Todo feature
// This class is responsible for creating the TodoApi, TodoDao, and TodoViewModel
// It also sets the content view to the TodoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val todoApi = Retrofit.Builder()
            .baseUrl(TodoApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TodoApi::class.java)

        val todoDao = Room.databaseBuilder(
            this,
            TodoDatabase::class.java,
            "todos.db"
        ).build().dao

        setContent {
            TodoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding),
                    ) {
                        TodoScreen(
                            TodoViewModel(TodoRepository(todoApi, todoDao))
                        )
                    }

                }
            }
        }
    }
}