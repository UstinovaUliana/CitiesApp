package com.ustinovauliana.citiesapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    @SerialName("name") val name: String,
    @SerialName("country") val country: String
)

fun CityDTO.mapToCity(): City =
    City(
        name = name,
        country = country
    )
