package com.nilo.dependencies

import org.gradle.api.Action
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.DependencyConstraint
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyConstraintHandler
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

@Suppress("unused", "EnumEntryName")
enum class NiloDependency {

    implementation,
    compileOnly,
    testImplementation,
    api; //TODO, add remaining dependency types

    operator fun invoke(block: NiloDependency.(NiloDependency) -> Any) {
        block(this)
    }

    fun DependencyHandler.fromExclude(
            dependencyNotation: String,
            dependencyConfiguration: Action<ExternalModuleDependency>
    ): ExternalModuleDependency = addDependencyTo(
            this, this@NiloDependency.name, dependencyNotation, dependencyConfiguration
    ) as ExternalModuleDependency

    fun DependencyConstraintHandler.from(vararg dependencyNotation: Any) = dependencyNotation.forEach { add(name, it) }

    fun DependencyConstraintHandler.fromStarter(vararg lists: List<Any>) = flattenConstraintForAll(*lists) {
        add(name, it)
    }

    fun DependencyHandler.from(vararg dependencyNotation: Any) = dependencyNotation.forEach { add(name, it) }

    fun DependencyHandler.fromPlatform(vararg dependencyNotation: Any) = dependencyNotation.forEach { add(name, platform(it)) }

    fun DependencyHandler.fromPlatformStarter(vararg lists: List<Any>) = flattenForAll(*lists) {
        add(name, platform(it))
    }
    fun DependencyHandler.fromStarter(vararg lists: List<Any>) = flattenForAll(*lists) {
        add(name, it)
    }

    fun DependencyHandler.forAllExtended(vararg lists: List<Pair<String, Action<ExternalModuleDependency>>>) = flattenForAllExtended(*lists) {
        addDependencyTo(this, name, it.first, it.second)
    }

    private fun flattenForAll(vararg lists: List<Any>, action: (Any) -> Dependency?) {
        lists
                .flatMap { it }
                .forEach { action(it) }
    }

    private fun flattenConstraintForAll(vararg lists: List<Any>, action: (Any) -> DependencyConstraint) {
        lists
                .flatMap { it }
                .forEach { action(it) }
    }

    private fun flattenForAllExtended(vararg lists: List<Pair<String, Action<ExternalModuleDependency>>>, action: (Pair<String, Action<ExternalModuleDependency>>) -> Dependency?) {
        lists
                .flatMap { it }
                .forEach { action(it) }
    }
}