package com.example.travelgallery.database.repository

import com.example.travelgallery.database.dao.PinDao
import com.example.travelgallery.database.entity.PinEntity
import kotlinx.coroutines.flow.Flow

class PinRepository(private val pinDao: PinDao) {
    suspend fun insertPinData(pin: PinEntity) = pinDao.insert(pin)

    fun getAllPinStream(): Flow<List<PinEntity>> = pinDao.getAllPins()
}
