package com.ustinovauliana.citiesapp.presentation.store

import com.ustinovauliana.citiesapp.presentation.integration.CitiesMain

internal fun SearchStore.State.mapStateToModel() : CitiesMain.Model  =
    CitiesMain.Model(
        query = query,
        citiesResult = citiesResult
    )
