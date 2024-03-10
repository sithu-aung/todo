package com.amity.todo.data.repository

import com.amity.todo.data.local.TodoDao
import com.amity.todo.data.local.TodoEntity
import com.amity.todo.data.remote.TodoApi
import com.amity.todo.domain.Todo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class TodoRepositoryTest {

    private lateinit var repository: TodoRepository
    private lateinit var dao: TodoDao
    private lateinit var service: TodoApi

    @Before
    fun setup() {
        // Mock the DAO and service
        dao = mockk(relaxed = true)
        service = mockk(relaxed = true)

        // Create the repository with the mocked dependencies
        repository = TodoRepository(service, dao)
    }

    @Test
    fun `getTodos should return data from database if available`() = runBlocking {
        // Arrange
        val databaseTodos = listOf(
            TodoEntity(1, 1, "Todo 1", false),
            TodoEntity(2, 1, "Todo 2", false),
        )
        coEvery { dao.getTodos() } returns flowOf(databaseTodos)

        // Act
        val result = repository.getTodosFromDatabase().first()

        // Assert
        assert(result == databaseTodos)
        coVerify { dao.getTodos() }
        coVerify(inverse = true) { service.getTodos() }
    }

    @Test
    fun `getTodos should fetch from API and store in database if database is empty`() = runBlocking {
        // Arrange
        coEvery { dao.getTodos() } returns flowOf(emptyList())
        val apiTodos = listOf(
            Todo(1, 1, "Todo 1", false),
            Todo(2, 1, "Todo 2", true)
        )
        coEvery { service.getTodos() } returns apiTodos

        // Act
        repository.fetchAndStoreTodos()

        val todoEntities = apiTodos.map { todo->
            TodoEntity(todo.id,todo.userId,todo.title,todo.completed)
        }

        coVerify { dao.getTodos() }
        coVerify { service.getTodos() }
        coVerify { dao.insertTodos(todoEntities) }
    }
}