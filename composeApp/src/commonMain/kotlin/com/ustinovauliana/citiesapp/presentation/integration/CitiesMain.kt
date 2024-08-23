package com.ustinovauliana.citiesapp.presentation.integration

import com.arkivanov.decompose.value.Value
import com.ustinovauliana.citiesapp.domain.models.City
import com.ustinovauliana.citiesapp.presentation.store.CitiesResult

interface CitiesMain {

    val models: Value<Model>

    data class Model(
        val query: String,
        val citiesResult: CitiesResult<List<City>>?
    )

    fun onQueryChange(query: String)
    fun onQueryCleared()
}
