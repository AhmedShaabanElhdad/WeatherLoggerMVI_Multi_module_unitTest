package com.example.domain.entity

import java.util.*

data class WeatherEntity(
    val id: Int,
    val name: String,
    val desc: String,
    val temp: Double,
    val date: Date = Date()
)