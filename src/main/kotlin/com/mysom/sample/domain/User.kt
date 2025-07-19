package com.mysom.sample.domain

@JvmInline
value class UserId(
    val value: Long,
)

@JvmInline
value class UserName(
    val value: String,
)

data class User(
    val name: UserName,
    val age: Int,
    val city: CityName? = null,
    val id: UserId? = null,
)
