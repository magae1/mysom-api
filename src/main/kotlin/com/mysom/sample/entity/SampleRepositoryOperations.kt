package com.mysom.sample.entity

import com.mysom.sample.domain.City
import com.mysom.sample.domain.User

interface SampleRepositoryOperations {
    fun findAllCities(): List<City>

    fun findAllUsers(): List<User>

    fun saveCity(city: City)

    fun saveUser(user: User)
}
