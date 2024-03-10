package com.amity.todo.data.remote

import com.amity.todo.domain.Todo
import retrofit2.http.GET
import retrofit2.http.Query

// API interface for the Todo feature
// This interface is responsible for defining the API endpoints

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}