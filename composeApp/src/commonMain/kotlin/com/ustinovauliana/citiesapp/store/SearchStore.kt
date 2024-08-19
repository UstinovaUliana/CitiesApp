package com.ustinovauliana.citiesapp.store

import com.arkivanov.mvikotlin.core.store.Store
import com.ustinovauliana.citiesapp.CitiesResult
import com.ustinovauliana.citiesapp.City

internal interface SearchStore: Store<SearchStore.Intent, SearchStore.State, Nothing> {
    sealed interface Intent {
        data class Search(val query: String): Intent
        object Clear: Intent
    }

    data class State(
        val query: String = "",
        val citiesResult: CitiesResult<List<City>>? = CitiesResult.Start()
    )
}
