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
import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.ustinovauliana.citiesapp.domain.repository.CitiesRepository
import com.ustinovauliana.citiesapp.presentation.store.SearchStore
import com.ustinovauliana.citiesapp.presentation.store.SearchStoreFactory
import com.ustinovauliana.citiesapp.presentation.store.mapStateToModel
import com.ustinovauliana.citiesapp.utils.asValue

class MainComponent(
    storeFactory: StoreFactory,
    repository: CitiesRepository,
    instanceKeeperDispatcher: InstanceKeeperDispatcher
) : CitiesMain {

    private val store = instanceKeeperDispatcher.getStore {
        SearchStoreFactory(
            storeFactory = storeFactory,
            citiesRepository = repository
        ).create()
    }

    override val models: Value<CitiesMain.Model> = store.asValue().map {
        it.mapStateToModel()
    }

    private val citiesSaver: Saver<EditableUserInputState, *> = listSaver(
        save = { listOf(it.queryState) },
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
        rememberSaveable(saver = citiesSaver) {
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
