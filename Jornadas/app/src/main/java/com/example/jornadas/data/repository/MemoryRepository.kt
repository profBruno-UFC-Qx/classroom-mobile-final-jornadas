package com.example.jornadas.data.repository

import com.example.jornadas.data.dao.MemoryDao
import com.example.jornadas.data.entities.Memory
import kotlinx.coroutines.flow.Flow

class MemoryRepository(private val memoryDao: MemoryDao) {
    fun getMemoriesStream(userId: String): Flow<List<Memory>> = memoryDao.getUserMemories(userId)

    suspend fun getMemory(id: Int): Memory? = memoryDao.getMemoryById(id)

    suspend fun insertMemory(memory: Memory) = memoryDao.insert(memory)

    suspend fun updateMemory(memory: Memory) = memoryDao.update(memory)

    suspend fun deleteMemoru(memory: Memory) = memoryDao.delete(memory)

}