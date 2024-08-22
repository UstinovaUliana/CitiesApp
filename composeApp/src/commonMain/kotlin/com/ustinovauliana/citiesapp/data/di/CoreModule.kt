package com.ustinovauliana.citiesapp.data.di

import org.kodein.di.DI

val coreModule = DI.Module("coreModule"){
    importAll(
        serializationModule,
        ktorModule,
    )
}
