package com.example.jornadas.viewmodels

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jornadas.JornadasApplication
import com.example.jornadas.viewmodels.home.HomeViewModel
import com.example.jornadas.viewmodels.memorycreation.MemoryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                JornadasApplication().container.repository
            )
        }

        initializer {
            MemoryViewModel(
                JornadasApplication().container.repository
            )
        }
    }
}

fun CreationExtras.JornadasApplication(): JornadasApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JornadasApplication)