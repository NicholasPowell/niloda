package com.nilo.cloudfxn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class CloudFunctions

fun main(args: Array<String>) {
    runApplication<CloudFunctions>(*args)
}



