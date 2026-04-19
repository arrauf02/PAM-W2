package com.example.pengembanganaplikasimobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pengembanganaplikasimobile.data.repository.NewsRepository
import com.example.pengembanganaplikasimobile.ui.state.UiState
import com.example.pengembanganaplikasimobile.data.model.Post
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init { loadNews() }

    fun loadNews() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val posts = repository.fetchPosts()
                _uiState.value = UiState.Success(posts)
            } catch (e: Exception) {
                // Menampilkan pesan error asli jika gagal
                _uiState.value = UiState.Error(e.message ?: "Gagal memuat berita")
            }
        }
    }
}