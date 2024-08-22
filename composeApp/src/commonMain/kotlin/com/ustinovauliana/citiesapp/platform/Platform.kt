package com.ustinovauliana.citiesapp.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
