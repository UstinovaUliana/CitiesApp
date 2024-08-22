package com.ustinovauliana.citiesapp.data

import com.ustinovauliana.citiesapp.domain.models.City


interface CitiesRepository {
    suspend fun searchCities(query: String): List<City>
}
