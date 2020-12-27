package com.nilo.container

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import java.io.File

object RegisterDockerBuildTask {

    const val taskName = "dockerBuild"

    operator fun invoke(project: Project): TaskProvider<DefaultTask> =
            project.registerDefaultTask(taskName) {
                setSubtaskGroup()
                doLast {
                    execDockerBuild(project)
                }
                dependsOn(RegisterCopySpringBootJarTask.taskName)
            }


    private fun execDockerBuild(project: Project) =
            getDockerBuildCommand(project)
                    .executeNoFail(project.getDockerOutputDir())
                    .printResults()

    private fun Project.getDockerOutputDir() =
            File("${buildDir}/${GenerateDockerFile.generateDir}")

    private fun getDockerBuildCommand(project: Project) =
            "docker build . -t ${formatAwsRepository(project)}"

    private fun formatAwsRepository(project: Project) =
            with(project.getContainerBootExtension()) {
                "${awsProjectId}.dkr.ecr.${awsRegion}.amazonaws.com/${awsRepositoryName}"
            }
}