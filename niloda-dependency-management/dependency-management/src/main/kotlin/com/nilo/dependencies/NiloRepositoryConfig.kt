package com.nilo.dependencies

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

object NiloRepositoryConfig {
    operator fun invoke(scope: RepositoryHandler) {
        with(scope) {
            mavenLocal()
            mavenCentral()
            jcenter()
            maven(url = "https://repo.spring.io/snapshot")
            maven(url = "https://repo.spring.io/milestone")
        }
    }
}

