package com.ustinovauliana.citiesapp

import kotlinx.serialization.Serializable

@Serializable
data class CitiesRequest (
    val query: String,
    val limit: String,
)
