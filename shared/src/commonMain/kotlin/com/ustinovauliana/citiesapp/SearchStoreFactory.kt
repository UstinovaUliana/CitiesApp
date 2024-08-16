package com.ustinovauliana.citiesapp

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchStoreFactory(private val storeFactory: StoreFactory) {

    fun create(): SearchStore =
        object : SearchStore, Store<SearchStore.Intent, SearchStore.State, Nothing> by storeFactory.create(
            name = "SearchStore",
            initialState = SearchStore.State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {

        }

    private sealed interface Msg {
        class Cities(val cities: List<SearchStore.City>?) : Msg
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

        private fun search(query: String?) {
            scope.launch {
                val cities = withContext(Dispatchers.IO) {
                    listOf(
                        SearchStore.City("Moscow", "Russia"),
                        SearchStore.City("Minsk", "Belarus"),
                        SearchStore.City("Paris", "France"),
                        SearchStore.City("Beigin", "China"),
                        SearchStore.City("Dubai", "OAE"),
                        SearchStore.City("London", "UK"),
                        SearchStore.City("Washington", "USA"),
                    )
                }
                dispatch(Msg.Cities(cities))
            }
        }
    }

}
