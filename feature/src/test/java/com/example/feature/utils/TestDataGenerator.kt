package com.example.feature.utils

import com.example.domain.entity.WeatherEntity
import com.example.feature.model.WeatherUiModel

class TestDataGenerator {

    companion object {
        fun generateWeatherItems() : WeatherEntity{
            return WeatherEntity(1, "cairo", "desc 1", 12.0005)
        }
    }

}