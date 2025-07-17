package com.mysom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MysomApplication

fun main(args: Array<String>) {
    runApplication<MysomApplication>(*args)
}
