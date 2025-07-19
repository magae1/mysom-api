package com.mysom.sample.domain

@JvmInline
value class CityId(
    val value: Long,
)

@JvmInline
value class CityName(
    val value: String,
)

data class City(
    val name: CityName,
    val id: CityId? = null,
)
