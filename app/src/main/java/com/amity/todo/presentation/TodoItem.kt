package com.amity.todo.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amity.todo.ui.theme.TodoTheme
import  com.amity.todo.R
import com.amity.todo.data.local.TodoEntity

// Todo item for the Todo feature
// This composable function is responsible for displaying a single todo item
// It takes a TodoEntity as a parameter and displays the title and status of the todo

@Composable
fun TodoItem(
    todo: TodoEntity,
) {
    Card(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    if (todo.completed)
                        Image(
                            painter = painterResource(id = R.drawable.checked),
                            "content description",
                            modifier = Modifier.size(24.dp)
                        )
                    else Image(
                        painter = painterResource(id = R.drawable.edit),
                        "content description",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Status: ${if (todo.completed) "Completed" else "In Progress"}",
                        style = MaterialTheme.typography.bodyMedium,
                    )


                }
            }
        }
    }
}

// Preview the TodoItem

@Preview
@Composable
fun TodoItemPreview() {
    TodoTheme {
        TodoItem(
            todo = TodoEntity(
                id = 1,
                userId = 1,
                title = "Title",
                completed = true
            ),
        )
    }
}