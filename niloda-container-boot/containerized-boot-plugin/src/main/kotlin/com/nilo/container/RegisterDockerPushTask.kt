package com.nilo.container

import org.gradle.api.Project

object RegisterDockerPushTask {

    const val taskName = "dockerPush"

    operator fun invoke(project: Project) =
            project.registerDefaultTask(taskName) {
                setTaskGroup()
                doLast {
                    execDockerPush(project)
                }
                dependsOn(RegisterDockerBuildTask.taskName)
                dependsOn(RegisterDockerLoginTask.taskName)
            }



    private fun execDockerPush(project: Project) =
        getDockerPushCommand(project)
                .executeNoFail()
                .printResults()


    private fun getDockerPushCommand(project: Project) =
            with(project.getContainerBootExtension()) {
                "docker push $awsProjectId.dkr.ecr.$awsRegion.amazonaws.com/$awsRepositoryName | grep -o \"sha256:[^ ]*\" > $awsDigestFilename"
            }
}