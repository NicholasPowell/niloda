package com.nilo.container

import org.gradle.api.Project

object AddBootBaseDependency {

    private const val pluginKey = "niloda-container-boot-plugin"
    private const val baseKey = "niloda-container-boot-base"
    private const val groupId = "com.nilo"

    operator fun invoke(project: Project) =
            addDependencyOnBootBase(
                    project,
                    getPluginVersionFromBuildscript(project))


    private fun addDependencyOnBootBase(project: Project, pluginVersion: String) =
            project.dependencies.add("api", formatBootBaseDependency(pluginVersion))

    private fun formatBootBaseDependency(pluginVersion: String) =
            "$groupId:$baseKey:$pluginVersion"

    private fun getPluginVersionFromBuildscript(project: Project) =
            project
                    .buildscript
                    .configurations
                    .flatMap { it.dependencies }
                    .first { it.name == pluginKey }
                    .version
                    ?: throw Exception("Missing dependency on $pluginKey, needed to determine version for $baseKey")
}