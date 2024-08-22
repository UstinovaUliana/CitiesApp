package com.ustinovauliana.citiesapp.domain

import com.ustinovauliana.citiesapp.data.CitiesRepository
import com.ustinovauliana.citiesapp.data.KtorCitiesDataSource
import com.ustinovauliana.citiesapp.data.models.mapToCity
import com.ustinovauliana.citiesapp.domain.models.City

class CitiesRepositoryImpl(
    private val remoteDataSource: KtorCitiesDataSource
): CitiesRepository {
    override suspend fun searchCities(query: String): List<City> {
        return remoteDataSource.searchCities(query).map{
                it.mapToCity()
            }
    }
}
