package com.mysom.sample.service

import com.mysom.sample.domain.City
import com.mysom.sample.domain.CityName
import com.mysom.sample.domain.User
import com.mysom.sample.domain.UserName
import com.mysom.sample.entity.SampleRepositoryOperations
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Service

@Service
class SampleService(
    val repo: SampleRepositoryOperations,
) : SampleUseCases {
    private val logger = KotlinLogging.logger {}

    override fun getCities(): List<City> {
        val cities = repo.findAllCities()
        logger.info { "Cities: $cities" }
        return cities
    }

    override fun getUsers(): List<User> {
        val users = repo.findAllUsers()
        logger.info { "Users: $users" }
        return users
    }

    override fun addUser(request: SampleUseCases.UserRequest) {
        transaction {
            addLogger(StdOutSqlLogger)
            repo.saveUser(
                User(
                    UserName(request.name),
                    request.age,
                    request.cityName?.let { CityName(request.cityName) },
                ),
            )
        }
    }

    override fun addCity(request: SampleUseCases.CityRequest) {
        transaction {
            addLogger(StdOutSqlLogger)
            repo.saveCity(
                City(
                    CityName(request.name),
                ),
            )
        }
    }
}
