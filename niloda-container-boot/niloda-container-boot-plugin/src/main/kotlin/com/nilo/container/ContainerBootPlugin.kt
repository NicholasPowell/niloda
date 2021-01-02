package com.nilo.container

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class ContainerBootPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        RegisterContainerBootExtension(project)
        GenerateDockerFile(project)
//        AddBootBaseDependency(project)
        RegisterDockerTasks(project)

    }

}

