package dev.silas.kontext

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KontextApplication

fun main(args: Array<String>) {
    runApplication<KontextApplication>(*args)
}
