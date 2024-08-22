package com.ustinovauliana.citiesapp.presentation

sealed class CitiesResult<T>{
    class InProgress<T> : CitiesResult<T>()
    class Success<T>(val data: T) : CitiesResult<T>()
    class Start<T>: CitiesResult<T>()
    class Error<T>(val exception: Exception) : CitiesResult<T>()
}
