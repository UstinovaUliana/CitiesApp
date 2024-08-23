package com.ustinovauliana.citiesapp.data.models

import com.ustinovauliana.citiesapp.domain.models.City

internal fun CityDTO.mapToCity(): City =
    City(
        name = name,
        country = country,
        population = population
    )
