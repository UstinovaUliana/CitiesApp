package com.ustinovauliana.citiesapp.domain.repository

import com.ustinovauliana.citiesapp.data.models.CitiesRequest
import com.ustinovauliana.citiesapp.domain.models.City

interface CitiesRepository {
    suspend fun searchCities(request: CitiesRequest): List<City>
}
