pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val shadowJarVersion: String by settings
    val dependencyManagementVersion: String by settings
    val kotlinSpringVersion: String by settings
    val palantirDockerVersion: String by settings
    plugins {
        java
        maven
        kotlin("jvm") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
        id("com.github.johnrengelman.shadow") version shadowJarVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
        id("org.jetbrains.kotlin.plugin.spring") version kotlinSpringVersion
        id("com.palantir.docker") version palantirDockerVersion
    }
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            println(requested.id)
            if (requested.id.id == "com.nilo.NiloDependencies") {
                useModule("com.nilo:dependency-management:0.0.1")
            }
        }
    }
}

include("niloda-container-boot-plugin")

val includeLocalDependencies = true
if( includeLocalDependencies ) {
    includeBuild("../niloda-dependency-management")
}

rootProject.name = "niloda-container-boot"
