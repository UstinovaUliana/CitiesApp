package com.ustinovauliana.citiesapp

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.ustinovauliana.citiesapp.store.SearchStore
import com.ustinovauliana.citiesapp.store.SearchStoreFactory

interface CitiesMain {

    val models: Value<Model>

    data class Model(
        val query: String,
        val citiesResult: CitiesResult<List<City>>?
    )

    fun onQueryChange(query: String)
    fun onQueryCleared()
}
internal val stateToModel: (SearchStore.State) -> CitiesMain.Model =
    {
        CitiesMain.Model(
            query = it.query,
            citiesResult = it.citiesResult
        )
    }

class MainComponent(
    storeFactory: StoreFactory,
    repository: CitiesRepository,
    ): CitiesMain {

    private val store =
        SearchStoreFactory(
            storeFactory = storeFactory,
            citiesRepository = repository
        ).create()

    override val models:  Value<CitiesMain.Model> = store.asValue().map(stateToModel)

    override fun onQueryChange(query: String) {
        store.accept(SearchStore.Intent.Search(query))
    }

    override fun onQueryCleared() {
        store.accept(SearchStore.Intent.Clear)
    }
}
