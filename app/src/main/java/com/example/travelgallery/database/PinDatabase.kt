package com.example.travelgallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelgallery.database.dao.PinDao
import com.example.travelgallery.database.entity.PinEntity

@Database(entities = [PinEntity::class], version = 1, exportSchema = false)
abstract class PinDatabase: RoomDatabase() {
    abstract fun pinDao(): PinDao

    companion object {
        @Volatile
        private var Instance: PinDatabase? = null

        fun getDatabase(context: Context): PinDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PinDatabase::class.java, "pin_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}