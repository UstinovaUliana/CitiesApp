package com.ustinovauliana.citiesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.ustinovauliana.citiesapp.data.CitiesRepository
import com.ustinovauliana.citiesapp.platform.DiTree.instance
import com.ustinovauliana.citiesapp.presentation.SearchMainView
import com.ustinovauliana.citiesapp.presentation.integration.MainComponent

@Composable
fun App() {

    val citiesRepository = instance<CitiesRepository>()

    MaterialTheme {

        val root = MainComponent(
            storeFactory = DefaultStoreFactory(),
            repository = citiesRepository)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SearchMainView(root)
        }

    }
}

