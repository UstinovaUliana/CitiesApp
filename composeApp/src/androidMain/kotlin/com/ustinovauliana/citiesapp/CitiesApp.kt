package com.ustinovauliana.citiesapp

import android.app.Application
import com.ustinovauliana.citiesapp.platform.PlatformSDK

class CitiesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }
}

fun CitiesApp.initPlatformSDK() =
    PlatformSDK.init(
        configuration = com.ustinovauliana.citiesapp.platform.PlatformConfiguration(androidContext = applicationContext)
    )
