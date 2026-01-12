package com.example.jornadas

import android.app.Application
import android.content.Context
import com.example.jornadas.data.AppDatabase
import com.example.jornadas.data.repository.MemoryRepository

interface AppContainer {
    val repository: MemoryRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val repository: MemoryRepository by lazy {
        MemoryRepository(AppDatabase.getDatabase(context).memoryDao())
    }
}

class JornadasApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)

    }
}