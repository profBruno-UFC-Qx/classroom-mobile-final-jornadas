package com.example.jornadas.viewmodels

import android.util.Patterns

object Validations {
    fun validateName(name: String): String? {
        if(name.isBlank()) return "Campo obrigatório"
        return null
    }

    fun validateEmail(email: String): String? {
        if(email.isBlank()) return "Campo obrigatório"
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "Email inválido"
        return null
    }

    fun validatePassword(password: String): String? {
        if(password.isBlank()) return "Campo obrigatório"
        if(password.length < 8) return "Senha deve ter no mínimo 8 caracteres"
        if(!password.any { it.isUpperCase() }) return "Senha deve ter pelo menos uma letra maiúscula"
        return null
    }

    fun validatePasswordLogin(password: String): String? {
        if(password.isBlank()) return "Campo obrigatório"
        return null
    }

    fun validateConfirm(password: String, confirmPass: String): String? {
        if(confirmPass.isBlank()) return "Campo obrigatório"
        if(password != confirmPass) return "As senhas devem ser iguais"
        return null
    }


}