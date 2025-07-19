package com.mysom.sample.service

import com.mysom.sample.domain.City
import com.mysom.sample.domain.User

interface SampleUseCases {
    fun getCities(): List<City>

    fun getUsers(): List<User>

    fun addUser(request: UserRequest)

    fun addCity(request: CityRequest)

    data class UserRequest(
        val name: String,
        val age: Int,
        val cityName: String?,
    )

    data class CityRequest(
        val name: String,
    )
}
