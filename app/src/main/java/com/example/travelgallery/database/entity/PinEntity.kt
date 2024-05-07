package com.example.travelgallery.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pin")
data class PinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val snippet: String,
    val latitude: String,
    val longitude: String,
)
