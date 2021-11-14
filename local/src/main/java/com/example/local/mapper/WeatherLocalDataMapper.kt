package com.example.local.mapper

import com.example.common.Mapper
import com.example.data.model.WeatherDTO
import com.example.local.model.WeatherLocalModel
import javax.inject.Inject

class WeatherLocalDataMapper @Inject constructor() : Mapper<WeatherLocalModel, WeatherDTO> {

    override fun from(i: WeatherLocalModel?): WeatherDTO {
        return WeatherDTO(
            id = i?.id ?:-1,
            name = i?.name ?:"",
            desc = i?.desc ?:"",
            temp = i?.temp ?:0.0
        )
    }

    override fun to(o: WeatherDTO?): WeatherLocalModel {
        return WeatherLocalModel(
            id = o?.id ?:-1,
            name = o?.name ?:"",
            desc = o?.desc ?:"",
            temp = o?.temp ?:0.0
        )
    }
}