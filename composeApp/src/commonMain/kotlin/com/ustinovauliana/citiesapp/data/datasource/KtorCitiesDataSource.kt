package com.ustinovauliana.citiesapp.data.datasource

import com.ustinovauliana.citiesapp.data.models.CitiesRequest
import com.ustinovauliana.citiesapp.data.models.CityDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorCitiesDataSource(val httpClient: HttpClient) {

    suspend fun searchCities(request: CitiesRequest): List<CityDTO> {

        return httpClient.get {
            url {
                contentType(ContentType.Application.Json)
                parameters.append("X-Api-Key", "Osi3deFjyyD902ARL2CjtQ==njRPX8YSl0ef6aPj")
                parameters.append("name", request.query)
                parameters.append("limit", request.limit)
            }
        }.body()
    }
}

