package com.example.feature.mapper

import com.example.common.Mapper
import com.example.domain.entity.WeatherEntity
import com.example.feature.core.formatToEgyptianDateTimeDefaults
import com.example.feature.model.WeatherUiModel
import java.text.SimpleDateFormat
import javax.inject.Inject


class WeatherDomainUiMapper @Inject constructor() : Mapper<WeatherEntity, WeatherUiModel> {

    override fun from(i: WeatherEntity?): WeatherUiModel {
        val celTemp = i?.temp?.toFloat()?.let { (it - 32.0f) * 5.0f / 9.0f }
        return WeatherUiModel(
            id = i?.id ?: 0,
            name = i?.name ?: "",
            desc = i?.desc ?: "",
            tempCel = "$celTemp",
            tempCelVal = celTemp ?: -1f,
            temp = "${i?.temp}",
            tempVal = i?.temp?.toFloat() ?: -1f,
            date = "${i?.date?.formatToEgyptianDateTimeDefaults()}"
        )
    }

    override fun to(o: WeatherUiModel?): WeatherEntity {
        return WeatherEntity(
            id = o?.id ?: 0,
            name = o?.name ?: "",
            desc = o?.desc ?: "",
            temp = o?.temp?.toDouble() ?: 0.0,
            date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(o?.date)
        )
    }
}