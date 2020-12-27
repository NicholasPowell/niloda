package com.nilo.dependencies

object Jackson {

    val core = WithVersion.core
    val moduleKotlin = WithVersion.moduleKotlin

    object WithVersion {
        const val core = "com.fasterxml.jackson.core:jackson-core:2.11.3"
        const val moduleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:2.11.3"
    }
    object WithoutVersion {
        val core = WithVersion.core.stripVersion()
        val moduleKotlin = WithVersion.moduleKotlin.stripVersion()
    }
}