package com.mysom.infrastructure.sql.entity

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable

object UserTable : LongIdTable("user") {
    const val MAX_NAME_LENGTH = 50

    val name = varchar("name", MAX_NAME_LENGTH)
    val age = integer("age")
    val cityId = optReference("city_id", CityTable)
}
