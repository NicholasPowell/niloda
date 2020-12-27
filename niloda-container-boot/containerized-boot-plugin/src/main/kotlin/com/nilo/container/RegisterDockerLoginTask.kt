package com.nilo.container

import org.gradle.api.Project

object RegisterDockerLoginTask {

    const val taskName = "dockerLogin"

    operator fun invoke(project: Project) {
        project.registerDefaultTask(taskName)
        {
            setSubtaskGroup()
            doLast {
                execDockerLogin(project)
            }
        }
    }

    private fun execDockerLogin(project: Project) =
            with(project.getContainerBootExtension()) {
                getDockerLoginCommand()
                        .executeNoFail()
                        .printResults()
            }


    private fun ContainerBootExtension.getDockerLoginCommand() =
            "aws ecr get-login-password --region $awsRegion | docker login --username AWS --password-stdin $awsProjectId.dkr.ecr.us-east-1.amazonaws.com/$awsRepositoryName"
}