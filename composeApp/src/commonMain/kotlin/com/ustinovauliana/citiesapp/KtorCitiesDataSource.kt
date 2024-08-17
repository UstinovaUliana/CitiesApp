package com.ustinovauliana.citiesapp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorCitiesDataSource(val httpClient: HttpClient) {
    suspend fun searchCities(query: String): List<CityDTO> {
        /*
        return listOf(City("Moscow", "Russia"),
            City("Minsk", "Belarus"),
            City("Paris", "France"),
            City("Beigin", "China"),
            City("Dubai", "OAE"),
            City("London", "UK"),
            City("Washington", "USA"),
        )

         */
        return httpClient.get {
            url {
                contentType(ContentType.Application.Json)
                parameters.append( "X-Api-Key","Osi3deFjyyD902ARL2CjtQ==njRPX8YSl0ef6aPj")
                parameters.append("name", query)
                parameters.append("limit", "100")
            }
        }.body()
    }
}
