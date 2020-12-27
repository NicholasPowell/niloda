package com.nilo.dependencies


object Kotlin {

    val logging = WithoutVersion.logging
    val sdk1_8 = WithoutVersion.sdk1_8

    object WithVersion {
        const val logging = "io.github.microutils:kotlin-logging:1.8.3"
        const val sdk1_8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72"
    }

    object WithoutVersion {
        val logging = WithVersion.logging.stripVersion()
        val sdk1_8 = WithVersion.sdk1_8.stripVersion()
    }
}