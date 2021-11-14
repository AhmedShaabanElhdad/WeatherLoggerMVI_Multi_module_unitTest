package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.WeatherDTO
import com.example.domain.entity.WeatherEntity
import javax.inject.Inject


class WeatherDataDomainMapper @Inject constructor() : Mapper<WeatherDTO, WeatherEntity> {

    override fun from(i: WeatherDTO?): WeatherEntity {
        return WeatherEntity(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            desc = i?.desc ?: "",
            temp = i?.temp ?: 0.0,
        )
    }

    override fun to(o: WeatherEntity?): WeatherDTO {
        return WeatherDTO(
            id = o?.id ?: -1,
            name = o?.name ?: "",
            desc = o?.desc ?: "",
            temp = o?.temp ?: 0.0,
        )
    }
}