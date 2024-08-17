package com.ustinovauliana.citiesapp.di

import com.ustinovauliana.citiesapp.CitiesRepository
import com.ustinovauliana.citiesapp.CitiesRepositoryImpl
import com.ustinovauliana.citiesapp.KtorCitiesDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val citiesModule = DI.Module("citiesModule") {
    bind<CitiesRepository>() with singleton {
        CitiesRepositoryImpl(instance())
    }

    bind<KtorCitiesDataSource>() with provider {
        KtorCitiesDataSource(instance())
    }
}
