package com.nilo.dependencies

object SpringBoot {

    val starterParent = WithoutVersion.starterParent
    val starterWebflux = WithoutVersion.starterWebflux
    val platform = WithVersion.platform
    val actuator = WithoutVersion.actuator

    object WithVersion {
        const val starterParent = "org.springframework.boot:spring-boot-starter-parent:2.3.3.RELEASE"
        const val starterWebflux = "org.springframework.boot:spring-boot-starter-webflux:2.3.3.RELEASE"
        const val starterMvc = "org.springframework.boot:spring-boot-starter-web:2.3.3.RELEASE"
        const val platform = "org.springframework.boot:spring-boot-dependencies:2.3.3.RELEASE"
        const val actuator = "org.springframework.boot:spring-boot-starter-actuator:2.3.3.RELEASE"
    }
    object WithoutVersion {
        val starterParent = WithVersion.starterParent.stripVersion()
        val starterWebflux = WithVersion.starterWebflux.stripVersion()
        val actuator = WithVersion.actuator.stripVersion()
    }

}