package com.nilo.container

import org.gradle.api.Project
import java.io.File
import java.nio.charset.Charset

object GenerateDockerFile {

    const val generateDir = "containerboot"

    operator fun invoke(project: Project) =
            createDockerfileInGenerateDir(project)


    private fun createDockerfileInGenerateDir(project: Project) =
            getTargetFile(project)
                    .apply {
                        ensureParentDirectoryPresent()
                        writeDockerfile()
                    }

    private fun File.ensureParentDirectoryPresent() =
            apply { parentFile.mkdirs() }

    private fun File.writeDockerfile() =
            bufferedWriter(Charset.defaultCharset()).use {
                it.write(getDockerfileContents())
            }

    private fun getTargetFile(project: Project) =
            File("${project.buildDir}/$generateDir/Dockerfile")

    private fun getDockerfileContents() = """
            FROM amazoncorretto:11-alpine
            COPY app.jar app.jar
            ENTRYPOINT ["java","-jar","app.jar"]
            """.trimIndent()
}