package com.example.jornadas.viewmodels.mapscreen

import com.google.android.gms.maps.model.LatLng

data class MapUiState(
    val selectedLocation: LatLng? = null,
    val selectedAddress: String = "",
    val isLoadingAddress: Boolean = false
)