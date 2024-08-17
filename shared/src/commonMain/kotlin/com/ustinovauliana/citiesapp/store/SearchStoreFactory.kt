package com.ustinovauliana.citiesapp.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ustinovauliana.citiesapp.CitiesRepository
import com.ustinovauliana.citiesapp.City
import com.ustinovauliana.citiesapp.di.DiTree.instance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchStoreFactory(private val storeFactory: StoreFactory) {

    fun create(): SearchStore =
        object : SearchStore, Store<SearchStore.Intent, SearchStore.State, Nothing> by storeFactory.create(
            name = "SearchStore",
            initialState = SearchStore.State(),
            executorFactory = SearchStoreFactory::ExecutorImpl,
            reducer = ReducerImpl
        ) {

        }

    private sealed interface Msg {
        class Cities(val cities: List<City>?) : Msg
    }

    private object ReducerImpl : Reducer<SearchStore.State, Msg> {
        override fun SearchStore.State.reduce(msg: Msg): SearchStore.State =
            when (msg) {
                is Msg.Cities -> copy(cities = msg.cities)
            }
    }

    private class ExecutorImpl : CoroutineExecutor<SearchStore.Intent, Nothing, SearchStore.State, Msg, Nothing>() {
        override fun executeIntent(intent: SearchStore.Intent) {
            when(intent) {
                is SearchStore.Intent.Search -> search(intent.query)
                is SearchStore.Intent.Clear -> dispatch(Msg.Cities(null))
            }
        }

        val citiesRepository = instance<CitiesRepository>()
        private fun search(query: String?) {
            scope.launch {
                val cities = withContext(Dispatchers.IO) {
                    if (query!=null)
                        citiesRepository.searchCities(query)
                    else
                        emptyList()
                }
                dispatch(Msg.Cities(cities))
            }
        }
    }

}
