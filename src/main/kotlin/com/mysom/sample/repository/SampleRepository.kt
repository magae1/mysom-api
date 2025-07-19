package com.mysom.sample.repository

import com.mysom.infrastructure.sql.entity.CityTable
import com.mysom.infrastructure.sql.entity.UserTable
import com.mysom.sample.domain.City
import com.mysom.sample.domain.CityId
import com.mysom.sample.domain.CityName
import com.mysom.sample.domain.User
import com.mysom.sample.domain.UserId
import com.mysom.sample.domain.UserName
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class SampleRepository : SampleRepositoryOperations {
    private val logger = KotlinLogging.logger {}

    override fun findAllCities(): List<City> =
        transaction {
            addLogger(StdOutSqlLogger)
            CityTable
                .selectAll()
                .map { City(CityName(it[CityTable.name]), CityId(it[CityTable.id].value)) }
        }

    override fun findAllUsers(): List<User> =
        transaction {
            addLogger(StdOutSqlLogger)
            UserTable
                .leftJoin(CityTable)
                .select(UserTable.name, UserTable.age, CityTable.name, UserTable.id)
                .map {
                    User(
                        UserName(it[UserTable.name]),
                        it[UserTable.age],
                        if (it[CityTable.name] == null) null else CityName(it[CityTable.name]),
                        UserId(it[UserTable.id].value),
                    )
                }
        }

    override fun saveCity(city: City) {
        CityTable.insert {
            it[name] = city.name.value
        }
    }

    override fun saveUser(user: User) {
        UserTable.insert {
            it[name] = user.name.value
            it[age] = user.age
            it[cityId] =
                if (user.city == null) {
                    null
                } else {
                    CityTable
                        .select(CityTable.id)
                        .where { CityTable.name eq user.city.value }
                        .firstOrNull()
                        ?.get(CityTable.id)
                }
        }
    }
}
