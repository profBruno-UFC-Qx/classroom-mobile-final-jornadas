package com.example.jornadas.viewmodels.register


import androidx.lifecycle.ViewModel
import com.example.jornadas.viewmodels.Validations
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onUserChange(myUser: String) {
        _uiState.update { it.copy(user = myUser) }
    }

    fun onEmailChange(myEmail: String) {
        _uiState.update { it.copy(email = myEmail, emailError = null) }
    }

    fun onPasswordChange(myPassword: String) {
        _uiState.update { it.copy(password = myPassword, passwordError = null) }
    }

    fun onConfirmPasswordChange(myConfirmPassword: String) {
        _uiState.update {
            it.copy(
                confirmPassword = myConfirmPassword,
                confirmPasswordError = null
            )
        }
    }

    fun validateFields(): Boolean {
        val state = _uiState.value

        val userResult = Validations.validateName(state.user)
        val emailResult = Validations.validateEmail(state.email)
        val passwordResult = Validations.validatePassword(state.password)
        val confirmPasswordResult = Validations.validateConfirm(
            state.password, state.confirmPassword
        )

        _uiState.update {
            it.copy(
                userError = userResult,
                emailError = emailResult,
                passwordError = passwordResult,
                confirmPasswordError = confirmPasswordResult
            )
        }

        return userResult == null &&
                emailResult == null &&
                passwordResult == null &&
                confirmPasswordResult == null
    }

    fun onRegister() {
        if (!validateFields()) {
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        val state = _uiState.value
        val auth = Firebase.auth

        auth.createUserWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _uiState.update { it.copy(isLoading = false, registerSuccess = true) }
                } else {
                    _uiState.update {
                        it.copy(
                            error = task.exception?.message ?: "Erro desconhecido",
                            isLoading = false
                        )
                    }
                }
            }
    }

    fun onRegisterConsumed() {
        _uiState.update { it.copy(registerSuccess = false) }
    }
}


