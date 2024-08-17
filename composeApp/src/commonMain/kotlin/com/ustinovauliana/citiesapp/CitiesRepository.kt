package com.ustinovauliana.citiesapp


interface CitiesRepository {
    suspend fun searchCities(query: String): List<City>
}
