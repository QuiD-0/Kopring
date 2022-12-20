package com.quid.kopring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KopringAppApplication

fun main(args: Array<String>) {
    runApplication<KopringAppApplication>(*args)
}