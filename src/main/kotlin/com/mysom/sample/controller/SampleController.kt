package com.mysom.sample.controller

import com.mysom.sample.domain.City
import com.mysom.sample.domain.User
import com.mysom.sample.service.SampleUseCases
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    val service: SampleUseCases,
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/cities")
    fun getCity(): List<City> = service.getCities()

    @GetMapping("/users")
    fun getUser(): List<User> = service.getUsers()

    @PostMapping("/users")
    fun addUser(
        @RequestBody request: SampleUseCases.UserRequest,
    ) {
        service.addUser(request)
    }

    @PostMapping("/cities")
    fun addCity(
        @RequestBody request: SampleUseCases.CityRequest,
    ) {
        service.addCity(request)
    }
}
