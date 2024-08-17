package com.ustinovauliana.citiesapp.android

import android.app.Application
import com.ustinovauliana.citiesapp.PlatformConfiguration
import com.ustinovauliana.citiesapp.PlatformSDK

class CitiesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }
}

fun CitiesApp.initPlatformSDK() =
    PlatformSDK.init(
        configuration = PlatformConfiguration(androidContext = applicationContext)
    )
