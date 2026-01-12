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
import kotlinx.coroutines.withContext
import java.util.Locale



class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState = _uiState.asStateFlow()

    fun selectLocation(latLng: LatLng) {
        _uiState.value = _uiState.value.copy(
            selectedLocation = latLng,
            isLoadingAddress = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(getApplication(), Locale.getDefault())

                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

                val enderecoFormatado = if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0]

                    val rua = address.thoroughfare ?: "Local sem rua"
                    val bairro = address.subLocality ?: ""

                    val cidade = address.locality ?: address.subAdminArea ?: ""
                    val estado = address.adminArea ?: ""

                    "$rua, $bairro, $cidade - $estado"
                } else {
                    "${latLng.latitude}, ${latLng.longitude}"
                }

                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(
                        selectedAddress = enderecoFormatado,
                        isLoadingAddress = false
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(
                        selectedAddress = "Erro ao buscar endereço",
                        isLoadingAddress = false
                    )
                }
            }
        }
    }
}