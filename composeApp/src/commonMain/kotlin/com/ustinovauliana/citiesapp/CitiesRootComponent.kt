package com.ustinovauliana.citiesapp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory

class CitiesRootComponent internal constructor(
    private val citiesMain: (ComponentContext) -> CitiesMain,
): CitiesRoot {

    constructor(
        storeFactory: StoreFactory,
        citiesRepository: CitiesRepository
    ) : this(
        citiesMain = {
            MainComponent(
                storeFactory = storeFactory,
                repository = citiesRepository,
            )
        },
    )
}
interface CitiesRoot {

    sealed class Child {
        data class Main(val component: CitiesMain) : Child()
    }
}
