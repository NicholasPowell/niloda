package com.nilo.dependencies

object SpringCloudFunction {

    val kotlin = WithoutVersion.kotlin
    val web = WithoutVersion.web

    object WithoutVersion {
        const val kotlin = "org.springframework.cloud:spring-cloud-function-kotlin"
        const val web = "org.springframework.cloud:spring-cloud-function-web"
    }
}