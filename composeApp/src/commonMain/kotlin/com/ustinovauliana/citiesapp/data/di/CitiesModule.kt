package com.ustinovauliana.citiesapp.data.di

import com.ustinovauliana.citiesapp.data.datasource.KtorCitiesDataSource
import com.ustinovauliana.citiesapp.data.repository.CitiesRepositoryImpl
import com.ustinovauliana.citiesapp.domain.repository.CitiesRepository
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
