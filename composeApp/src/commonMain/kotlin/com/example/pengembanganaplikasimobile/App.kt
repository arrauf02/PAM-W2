package com.example.pengembanganaplikasimobile

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.pengembanganaplikasimobile.data.repository.NewsRepository
import com.example.pengembanganaplikasimobile.ui.screen.NewsScreen
import com.example.pengembanganaplikasimobile.viewmodel.NewsViewModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val client = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

@Composable
fun App() {
    MaterialTheme {
        val repository = remember { NewsRepository(client) }
        val viewModel = remember { NewsViewModel(repository) }
        NewsScreen(viewModel)
    }
}