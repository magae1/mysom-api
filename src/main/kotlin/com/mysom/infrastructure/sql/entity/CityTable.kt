package com.mysom.infrastructure.sql.entity

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable

object CityTable : LongIdTable("city") {
    const val MAX_NAME_LENGTH = 50

    val name = varchar("name", MAX_NAME_LENGTH).uniqueIndex()
}
