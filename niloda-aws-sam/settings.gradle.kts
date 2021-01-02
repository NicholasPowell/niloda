pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val shadowJarVersion: String by settings
    val dependencyManagementVersion: String by settings
    val kotlinSpringVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
        id("com.github.johnrengelman.shadow") version shadowJarVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
        java
        maven
        id("org.jetbrains.kotlin.plugin.spring") version kotlinSpringVersion
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
            if(requested.id.id == "com.nilo.AwsSam") {
                useModule("com.nilo:aws-sam-plugin:0.0.1")
            }
            if(requested.id.id == "com.nilo.AwsCloudStack") {
                useModule("com.nilo:aws-cloud-stack-plugin:0.0.1")
            }
            if(requested.id.id == "com.nilo.ContainerBoot") {
                useModule("com.nilo:niloda-container-boot-plugin:0.0.1")
            }
        }
    }
}

include("niloda-aws-lambda-plugin")
include("niloda-aws-k8-plugin")

val includeLocalDependencies: Boolean = true
if( includeLocalDependencies ) {
    includeBuild("../niloda-dependency-management")
}

rootProject.name = "niloda-aws-sam"