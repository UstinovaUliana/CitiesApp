package com.ustinovauliana.citiesapp.presentation.integration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.ustinovauliana.citiesapp.data.CitiesRepository
import com.ustinovauliana.citiesapp.domain.models.City
import com.ustinovauliana.citiesapp.presentation.CitiesResult
import com.ustinovauliana.citiesapp.presentation.store.SearchStore
import com.ustinovauliana.citiesapp.presentation.store.SearchStoreFactory
import com.ustinovauliana.citiesapp.utils.asValue

interface CitiesMain {

    val models: Value<Model>

    data class Model(
        val query: String,
        val citiesResult: CitiesResult<List<City>>?
    )

    fun onQueryChange(query: String)
    fun onQueryCleared()
}
internal val stateToModel: (SearchStore.State) -> CitiesMain.Model = {
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
    /*val modelFlow =
        store.
            stateFlow.filter { state ->
                state.query == EditableUserInputState().queryState
            }
        .last()

     */

    val Saver: Saver<EditableUserInputState, *> = listSaver(
        save = { listOf(it.queryState)},
        restore = {
            EditableUserInputState()
        }
    )

    inner class EditableUserInputState {
        var queryState by mutableStateOf("")

        fun updateText(newText: String) {
            queryState = newText
            onQueryChange(newText)
        }
        fun clearText() {
            queryState = ""
            onQueryCleared()
        }
    }

    @Composable
    fun rememberEditableUserInputState(): EditableUserInputState =
        rememberSaveable(saver = Saver) {
            EditableUserInputState()
        }

    override fun onQueryChange(query: String) {
        if (query.isEmpty()) store.accept(SearchStore.Intent.Clear)
        else store.accept(SearchStore.Intent.Search(query))
    }

    override fun onQueryCleared() {
        EditableUserInputState().queryState = ""
        store.accept(SearchStore.Intent.Clear)
    }


}
