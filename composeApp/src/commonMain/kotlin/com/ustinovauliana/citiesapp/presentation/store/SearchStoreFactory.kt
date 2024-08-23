package com.ustinovauliana.citiesapp.presentation.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.ustinovauliana.citiesapp.data.models.CitiesRequest
import com.ustinovauliana.citiesapp.domain.models.City
import com.ustinovauliana.citiesapp.domain.repository.CitiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchStoreFactory(
    private val storeFactory: StoreFactory,
    private val citiesRepository: CitiesRepository
) {

    fun create(): SearchStore =
        object : SearchStore,
            Store<SearchStore.Intent, SearchStore.State, Nothing> by storeFactory.create(
                name = "SearchStore",
                initialState = SearchStore.State(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {
        }

    private sealed interface Msg {
        class CitiesLoaded(val query: String, val citiesResult: CitiesResult<List<City>>?) : Msg
    }

    private object ReducerImpl : Reducer<SearchStore.State, Msg> {
        override fun SearchStore.State.reduce(msg: Msg): SearchStore.State =
            when (msg) {
                is Msg.CitiesLoaded -> copy(query = msg.query, citiesResult = msg.citiesResult)
            }
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<SearchStore.Intent, Nothing, SearchStore.State, Msg, Nothing>() {
        override fun executeIntent(intent: SearchStore.Intent) {
            scope.coroutineContext.cancelChildren()
            when (intent) {
                is SearchStore.Intent.Search -> search(intent.query)
                is SearchStore.Intent.Clear -> clear()
            }
        }

        private fun clear() {
            dispatch(Msg.CitiesLoaded("", CitiesResult.Start()))
        }

        private fun search(query: String) {
            dispatch(Msg.CitiesLoaded(query, CitiesResult.InProgress()))
            scope.launch {
                val citiesResult = withContext(Dispatchers.IO) {
                    try {
                        CitiesResult.Success(
                            data = citiesRepository.searchCities(
                                CitiesRequest(
                                    query
                                )
                            )
                        )
                    } catch (e: Exception) {
                        CitiesResult.Error(e)
                    }
                }
                dispatch(Msg.CitiesLoaded(query, citiesResult))
            }
        }
    }

}
