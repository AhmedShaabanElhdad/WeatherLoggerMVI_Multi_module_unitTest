package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.WeatherDTO
import com.example.remote.model.WeatherResponseNetwork
import javax.inject.Inject


class WeatherNetworkDataMapper @Inject constructor() :
    Mapper<WeatherResponseNetwork, WeatherDTO> {

    override fun from(i: WeatherResponseNetwork?): WeatherDTO {
        return WeatherDTO(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            desc = i?.weather?.get(0)?.description ?: "",
            temp = i?.main?.temp ?: 0.0
        )
    }

    override fun to(o: WeatherDTO?): WeatherResponseNetwork {
        return WeatherResponseNetwork()
    }
}