package com.example.jornadas.viewmodels.mapscreen

import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale



class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState = _uiState.asStateFlow()

    private val geocoder by lazy {
        Geocoder(getApplication(), Locale.getDefault())
    }

    fun selectLocation(location: LatLng) {
        _uiState.value = _uiState.value.copy(
            selectedLocation = location,
            isLoadingAddress = true,
            selectedAddress = "Buscando endereço..."
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                val addressText = if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0]
                    "${address.thoroughfare ?: "Rua sem nome"}, ${address.subLocality ?: ""}"
                } else {
                    "Endereço não encontrado"
                }

                _uiState.value = _uiState.value.copy(
                    selectedAddress = addressText,
                    isLoadingAddress = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    selectedAddress = "Erro ao carregar endereço",
                    isLoadingAddress = false
                )
            }
        }
    }
}