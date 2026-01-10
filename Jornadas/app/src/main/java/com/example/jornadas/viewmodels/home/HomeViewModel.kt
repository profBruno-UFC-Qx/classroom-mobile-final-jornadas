package com.example.jornadas.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jornadas.data.entities.Memory
import com.example.jornadas.data.repository.MemoryRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(val memoryList: List<Memory> = listOf())

class HomeViewModel(private val repository: MemoryRepository): ViewModel() {
    private val userId = Firebase.auth.currentUser?.uid?: ""

    val homeUiState: StateFlow<HomeUiState> = repository.getMemoriesStream(userId)
        .map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState()
        )
}