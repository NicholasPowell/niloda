package com.nilo.dependencies

import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.java
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.maven
import org.gradle.plugin.use.PluginDependenciesSpec

object NiloPluginConfig {
    fun invoke(scope: PluginDependenciesSpecScope) {
        with(scope) {
            java
            maven
            kotlin("jvm")
            id("org.springframework.boot")
            id("org.jetbrains.kotlin.plugin.spring")
        }
    }
}