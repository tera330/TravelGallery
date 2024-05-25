package com.example.travelgallery.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.travelgallery.database.entity.PinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PinDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pin: PinEntity): Long

    @Query("SELECT * from pin WHERE id = :id")
    fun getPInStream(id: Long): Flow<PinEntity>

    @Query("SELECT * from pin")
    fun getAllPins(): Flow<List<PinEntity>>

    @Update
    suspend fun update(pinEntity: PinEntity)
}
