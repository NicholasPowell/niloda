package com.nilo.dependencies

object SpringCloudGateway {

    val server = WithoutVersion.server

    object WithVersion {
        const val server = WithoutVersion.server + ":" + "2.2.6.RELEASE"
    }

    object WithoutVersion {
        const val server = "org.springframework.cloud:spring-cloud-gateway-server"
    }
}