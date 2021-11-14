package com.example.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class WeatherUiModel(
    val id: Int,
    val name: String,
    val desc: String,
    val tempVal: Float,
    val temp: String,
    val tempCelVal: Float,
    val tempCel: String,
    val date: String
) : Parcelable