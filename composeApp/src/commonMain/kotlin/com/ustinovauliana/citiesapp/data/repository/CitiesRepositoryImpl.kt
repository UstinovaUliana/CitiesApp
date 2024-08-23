package com.ustinovauliana.citiesapp.data.repository

import com.ustinovauliana.citiesapp.data.datasource.KtorCitiesDataSource
import com.ustinovauliana.citiesapp.data.models.CitiesRequest
import com.ustinovauliana.citiesapp.data.models.mapToCity
import com.ustinovauliana.citiesapp.domain.models.City
import com.ustinovauliana.citiesapp.domain.repository.CitiesRepository

class CitiesRepositoryImpl(
    private val remoteDataSource: KtorCitiesDataSource
) : CitiesRepository {
    override suspend fun searchCities(request: CitiesRequest): List<City> {
        return remoteDataSource.searchCities(request).map {
            it.mapToCity()
        }
    }
}
