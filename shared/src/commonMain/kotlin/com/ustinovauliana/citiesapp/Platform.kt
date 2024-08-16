package com.ustinovauliana.citiesapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform