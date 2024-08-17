package com.ustinovauliana.citiesapp

import com.ustinovauliana.citiesapp.di.DiTree
import com.ustinovauliana.citiesapp.di.citiesModule
import com.ustinovauliana.citiesapp.di.coreModule
import org.kodein.di.DI
import org.kodein.di.direct

object PlatformSDK {
    fun init(
        configuration: PlatformConfiguration
    ) {
        DiTree.createDependencies(
            DI {
                importAll(
                    coreModule,
                    citiesModule
                )
            }.direct
        )
    }
}
