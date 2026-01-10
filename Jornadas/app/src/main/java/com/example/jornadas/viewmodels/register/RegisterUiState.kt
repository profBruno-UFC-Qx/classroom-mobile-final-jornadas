package com.example.jornadas.viewmodels.register

data class RegisterUiState(
    val user: String = "",
    val userError: String? = null,


    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,


    val isLoading: Boolean = false,
    val error: String? = null,
    val registerSuccess: Boolean = false
)
