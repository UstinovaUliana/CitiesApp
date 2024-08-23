package com.ustinovauliana.citiesapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CitiesRequest (
    val query: String,
    val limit: String = "100",
)
