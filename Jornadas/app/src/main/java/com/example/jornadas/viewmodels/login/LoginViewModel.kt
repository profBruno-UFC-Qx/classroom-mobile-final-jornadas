package com.example.jornadas.viewmodels.login


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jornadas.viewmodels.Validations
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

        val state = _uiState.value
        val auth = Firebase.auth

        auth.signInWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    _uiState.update { it.copy(isLoading = false, loginSuccess = true) }
                } else {
                    _uiState.update { it.copy(
                        error = task.exception?.message ?: "Falha ao fazer login",
                        isLoading = false
                    ) }
                }
            }

    }
    fun resetPassword(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("LoginViewModel", "Email sent.")
                }
            }
    }

    fun onLoginConsumed() {
        _uiState.update { it.copy(loginSuccess = false) }
    }
}