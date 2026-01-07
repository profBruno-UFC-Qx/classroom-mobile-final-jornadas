package com.example.jornadas.viewmodels.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jornadas.viewmodels.Validations
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
        val state = _uiState.value

        val emailResult = Validations.validateEmail(state.email)
        val passwordResult = Validations.validatePasswordLogin(state.password)

        _uiState.update { it.copy(
            emailError = emailResult,
            passwordError = passwordResult
        ) }

        return emailResult == null && passwordResult == null
    }

    fun onLogin() {
        if(!validateFields()) {
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(isLoading = false, loginSuccess = true) }
        }
    }

    fun onLoginConsumed() {
        _uiState.update { it.copy(loginSuccess = false) }
    }
}