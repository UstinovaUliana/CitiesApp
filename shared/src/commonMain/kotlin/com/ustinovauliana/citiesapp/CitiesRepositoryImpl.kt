package com.ustinovauliana.citiesapp

class CitiesRepositoryImpl(
    private val remoteDataSource: KtorCitiesDataSource
): CitiesRepository {
    override suspend fun searchCities(query: String): List<City> {
        return remoteDataSource.searchCities(query).map{it.mapToCity()}
    }
}
