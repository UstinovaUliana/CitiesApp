package com.ustinovauliana.citiesapp.data.models

import com.ustinovauliana.citiesapp.domain.models.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    @SerialName("name") val name: String,
    @SerialName("country") val country: String,
    @SerialName("population") val population: Int? = null,
)

fun CityDTO.mapToCity(): City =
    City(
        name = name,
        country = country,
        population = population
    )
