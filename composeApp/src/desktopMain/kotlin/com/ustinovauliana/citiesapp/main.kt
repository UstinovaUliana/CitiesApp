package com.ustinovauliana.citiesapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CitiesAppMP",
    ) {
        PlatformSDK.init(
            configuration = PlatformConfiguration()
        )
        App()
    }
}
