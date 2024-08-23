package com.ustinovauliana.citiesapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    @SerialName("name") val name: String,
    @SerialName("country") val country: String,
    @SerialName("population") val population: Int? = null,
)


