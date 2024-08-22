package com.ustinovauliana.citiesapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ustinovauliana.citiesapp.platform.PlatformSDK

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CitiesAppMP",
    ) {
        PlatformSDK.init(
            configuration = com.ustinovauliana.citiesapp.platform.PlatformConfiguration()
        )
        App()
    }
}
