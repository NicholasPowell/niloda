package com.nilo.container

import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.register
import java.io.File

object RegisterCopySpringBootJarTask {

    const val taskName = "copyBootJarForDocker"
    private const val bootJarTaskName = "bootJar"

    operator fun invoke(project: Project) =
            project.tasks.register<Copy>(taskName) {
                hideFromGroup()
                getBootJarFromPlugin(project)
                into(getOutputDir(project))
                dependsOn(bootJarTaskName)
            }

    private fun getOutputDir(project: Project) =
            File("${project.buildDir}/${GenerateDockerFile.generateDir}")

    private fun Copy.getBootJarFromPlugin(project: Project) =
            getSpringBootPluginArtifact(project)
                    .apply {
                        from("build/libs")
                        rename(".*", "app.jar")
                        include(name)
                    }

    private fun getSpringBootPluginArtifact(project: Project) =
            project.configurations.findByName("bootArchives")
                    ?.artifacts
                    ?.files
                    ?.singleFile
                    ?: throw Exception("${ContainerBootPlugin::class.simpleName} requires Spring Boot plugin --> id(\"org.springframework.boot\")")

}