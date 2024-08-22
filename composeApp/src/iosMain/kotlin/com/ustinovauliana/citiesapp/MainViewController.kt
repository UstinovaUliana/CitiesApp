package com.ustinovauliana.citiesapp

import androidx.compose.ui.window.ComposeUIViewController
import com.ustinovauliana.citiesapp.platform.PlatformConfiguration
import com.ustinovauliana.citiesapp.platform.PlatformSDK

fun MainViewController() = ComposeUIViewController {
    PlatformSDK.init(
        configuration = PlatformConfiguration()
    )
    App()
}
