package com.ustinovauliana.citiesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.ustinovauliana.citiesapp.domain.repository.CitiesRepository
import com.ustinovauliana.citiesapp.platform.DiTree.instance
import com.ustinovauliana.citiesapp.presentation.integration.MainComponent
import com.ustinovauliana.citiesapp.presentation.ui.SearchMainView
import com.ustinovauliana.citiesapp.presentation.ui.theme.CitiesTheme

@Composable
fun App() {

    val citiesRepository = instance<CitiesRepository>()
    val instanceKeeperDispatcher = InstanceKeeperDispatcher()

    CitiesTheme {

        val mainComponent = MainComponent(
            storeFactory = DefaultStoreFactory(),
            repository = citiesRepository,
            instanceKeeperDispatcher
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SearchMainView(mainComponent)
        }
    }
}

