package com.ustinovauliana.citiesapp

import com.arkivanov.mvikotlin.core.view.MviView

interface SearchView : MviView<SearchView.Model, SearchView.Event> {

    data class Model(
        val text: String,
        val cities: List<City>?
    )

    sealed class Event {
        object changeQuery : Event()
        object clearQuery : Event()
    }
}
