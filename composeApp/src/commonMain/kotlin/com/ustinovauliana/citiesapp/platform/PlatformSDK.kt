package com.ustinovauliana.citiesapp.platform

import com.ustinovauliana.citiesapp.data.di.citiesModule
import com.ustinovauliana.citiesapp.data.di.coreModule
import org.kodein.di.DI
import org.kodein.di.direct

object PlatformSDK {
    fun init(configuration: PlatformConfiguration) {
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
