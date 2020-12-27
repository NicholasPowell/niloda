package com.nilo.container

import org.gradle.api.Project

object RegisterContainerBootExtension{
    operator fun invoke(project: Project) {
        project.extensions.add(ContainerBootExtension.name, ContainerBootExtension::class.java)
    }
}