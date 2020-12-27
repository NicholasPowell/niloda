package com.nilo.dependencies

object JUnit5 {

    val engine = WithoutVersion.engine
    val api = WithoutVersion.api
    val params = WithoutVersion.params

    object WithVersion {
        const val engine = "org.junit.jupiter:junit-jupiter-engine:5.4.2"
        const val api = "org.junit.jupiter:junit-jupiter-api:5.4.2"
        const val params = "org.junit.jupiter:junit-jupiter-params:5.4.2"
    }
    object WithoutVersion {
        val engine = WithVersion.engine.stripVersion()
        val api = WithVersion.api.stripVersion()
        val params = WithVersion.params.stripVersion()
    }
}