package com.example.data.utils

import com.example.data.model.WeatherDTO


class TestDataGenerator {

    companion object {
        fun generateWeather() : WeatherDTO {
            return WeatherDTO(1, "cairo", "desc",299.57)
        }



        fun generateWeatherItems() : List<WeatherDTO> {
            return listOf(
                WeatherDTO(1, "cairo", "desc",299.57),
                WeatherDTO(1, "cairo", "desc",299.57),
                WeatherDTO(1, "cairo", "desc",299.57)
            )
        }

    }

}