package com.example.jornadas.viewmodels.login

data class LoginUiState(
    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false
)
