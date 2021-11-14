package com.example.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather")
data class WeatherLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val desc: String,
    val temp: Double
)