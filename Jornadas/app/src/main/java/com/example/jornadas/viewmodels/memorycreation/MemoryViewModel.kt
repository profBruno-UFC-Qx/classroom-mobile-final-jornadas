package com.example.jornadas.viewmodels.memorycreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jornadas.data.entities.Memory
import com.example.jornadas.data.repository.MemoryRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MemoryViewModel(private val repository: MemoryRepository): ViewModel() {
    private val _uiState = MutableStateFlow(MemoryUiState())

    val uiState: StateFlow<MemoryUiState> = _uiState.asStateFlow()

    private var currentMemoryId: String = ""

    fun onTitleChange(myTitle: String) {
        _uiState.update { it.copy(title = myTitle) }
    }

    fun onDescriptionChange(myDescription: String) {
        _uiState.update { it.copy(description = myDescription) }
    }

    fun onDateChange(myDate: String) {
        _uiState.update { it.copy(date = myDate) }
    }

    fun onLocationChange(myLocation: String) {
        _uiState.update { it.copy(location = myLocation) }
    }

    fun onLatLongChange(lat: Double, long: Double) {
        _uiState.update { it.copy(latitude = lat, longitude = long) }
    }

    fun onImageUriChange(myImageUri: String?) {
        _uiState.update { it.copy(imageUri = myImageUri) }
    }

    fun saveMemory() {
        val state = _uiState.value

        val userId = Firebase.auth.currentUser?.uid?: return

        val finalDate = try {
            LocalDate.parse(state.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } catch(e: Exception) {
            LocalDate.now()
        }

        val newMemory = Memory(
            userId = userId,
            title = state.title,
            description = state.description,
            date = finalDate,
            location = state.location.ifEmpty { "Localização não informada" },
            latitude = state.latitude,
            longitude = state.longitude,
            imageUri = state.imageUri
        )

        viewModelScope.launch {
           repository.insertMemory(newMemory)
            _uiState.update { it.copy(isSaved = true) }
        }

    }
    fun loadMemory(memoryId: String) {
        currentMemoryId = memoryId
        viewModelScope.launch {
            val memory = repository.getMemory(memoryId.toInt())
            memory?.let { mem ->
                _uiState.update { currentState ->
                    currentState.copy(
                        title = mem.title,
                        description = mem.description,
                        date = mem.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        location = mem.location,
                        latitude = mem.latitude,
                        longitude = mem.longitude,
                        imageUri = mem.imageUri
                    )
                }
            }
        }
    }

    fun updateMemory() {
        val state = _uiState.value
        val userId = Firebase.auth.currentUser?.uid ?: return

        val finalDate = try {
            LocalDate.parse(state.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } catch(e: Exception) {
            LocalDate.now()
        }

        val updatedMemory = Memory(
            id = currentMemoryId.toInt(),
            userId = userId,
            title = state.title,
            description = state.description,
            date = finalDate,
            location = state.location,
            latitude = state.latitude,
            longitude = state.longitude,
            imageUri = state.imageUri
        )

        viewModelScope.launch {
            repository.updateMemory(updatedMemory)
        }
    }
}