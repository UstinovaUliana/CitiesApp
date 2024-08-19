package com.ustinovauliana.citiesapp.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ustinovauliana.citiesapp.CitiesRepository
import com.ustinovauliana.citiesapp.CitiesResult
import com.ustinovauliana.citiesapp.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchStoreFactory(private val storeFactory: StoreFactory,
                                  private val citiesRepository: CitiesRepository) {

    fun create(): SearchStore =
        object : SearchStore, Store<SearchStore.Intent, SearchStore.State, Nothing> by storeFactory.create(
            name = "SearchStore",
            initialState = SearchStore.State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {

        }

    private sealed interface Msg {
        class CitiesLoaded(val citiesResult: CitiesResult<List<City>>?) : Msg
    }

    private object ReducerImpl : Reducer<SearchStore.State, Msg> {
        override fun SearchStore.State.reduce(msg: Msg): SearchStore.State =
            when (msg) {
                is Msg.CitiesLoaded -> copy(citiesResult = msg.citiesResult)
            }
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<SearchStore.Intent, Nothing, SearchStore.State, Msg, Nothing>() {
        override fun executeIntent(intent: SearchStore.Intent) {
            when(intent) {
                is SearchStore.Intent.Search -> search(intent.query)
                is SearchStore.Intent.Clear -> clear()

            }
        }

        private fun clear() {
            dispatch(Msg.CitiesLoaded(CitiesResult.Start()))
        }
        private fun search(query: String) {
            if (query.isNotEmpty()) {
                dispatch(Msg.CitiesLoaded(CitiesResult.InProgress()))
                scope.launch {
                    val citiesResult = withContext(Dispatchers.IO) {
                        try {
                            CitiesResult.Success(data = citiesRepository.searchCities(query))
                        } catch (e: Exception) {
                            CitiesResult.Error(e)
                        }

                    }
                    dispatch(Msg.CitiesLoaded(citiesResult))
                }
            }
            else
                dispatch(Msg.CitiesLoaded(CitiesResult.Start()))
        }
    }

}
