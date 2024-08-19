package com.ustinovauliana.citiesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.ustinovauliana.citiesapp.di.DiTree.instance

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

