package com.example.jornadas.viewmodels.login


import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun onEmailChange(myEmail: String) {
        _uiState.update { it.copy(email = myEmail, emailError = null) }
    }

    fun onPasswordChange(myPassword: String) {
        _uiState.update { it.copy(password = myPassword, passwordError = null) }
    }

    fun validateFields(): Boolean {
        val email = _uiState.value.email
        val password = _uiState.value.password
        var isValid = true

        if(email.isEmpty()) {
            _uiState.update { it.copy(emailError = "Campo obrigatório") }
            isValid = false
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.update {it.copy(emailError = "Email inválido")}
            isValid = false
        }

        if(password.isEmpty()) {
            _uiState.update {it.copy(passwordError = "Campo obrigatório")}
        }

        return isValid
    }

    fun onLogin() {
        if(!validateFields()) {
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(isLoading = false, loginSucess = true) }
        }
    }

    fun onLoginConsumed() {
        _uiState.update { it.copy(loginSucess = false) }
    }
}