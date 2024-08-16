package com.ustinovauliana.citiesapp

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.store.Store

internal interface SearchStore: Store<SearchStore.Intent, SearchStore.State, Nothing> {
    sealed interface Intent {
        data class Search(val query: String?): Intent
        object Clear: Intent
    }

    data class State(
        val cities: List<City>? = null
    )

    data class City(
        val name: String,
        val country: String
    )
}
