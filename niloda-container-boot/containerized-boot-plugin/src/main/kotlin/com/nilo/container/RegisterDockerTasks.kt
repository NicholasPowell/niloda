package com.nilo.container

import org.gradle.api.Project
import java.io.File


object RegisterDockerTasks {
    operator fun invoke(project: Project) {

        RegisterCopySpringBootJarTask(project)
        RegisterDockerLoginTask(project)
        RegisterDockerBuildTask(project)
        RegisterDockerPushTask(project)

    }
}

