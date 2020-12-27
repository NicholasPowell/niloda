package com.nilo.container

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class BootApp {

    @GetMapping()
    fun test() = "123"

}

fun main(args: Array<String>) {
    runApplication<BootApp>(*args)
}