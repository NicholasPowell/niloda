package com.nilo.container

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import java.io.File
import java.util.concurrent.TimeUnit


fun Project.getContainerBootExtension() =
        project.extensions.getByType<ContainerBootExtension>()

fun String.executeNoFail(workingDir: File = File(".")): Process? =
        wrapInTryCatchNull {
            createProcess(workingDir)
                    .start()
                    .waitForLongTime()
        }

fun <T> wrapInTryCatchNull( block: ()->T ) =
        try {
            block()
        } catch(e: Exception) {
            println(e)
            null
        }

private fun String.createProcess(workingDir: File) =
        ProcessBuilder(*wrapCommandInArray())
                .apply {
                    directory(workingDir)
                    redirectOutput(ProcessBuilder.Redirect.PIPE)
                    redirectError(ProcessBuilder.Redirect.PIPE)
                }

// TODO - handle in Windows
private fun String.wrapCommandInArray() =
        listOf("/bin/sh", "-c", this).toTypedArray()

private fun Process?.waitForLongTime() =
        apply { this?.waitFor(60, TimeUnit.MINUTES) }


fun Process?.printResults() =
    this?.apply {
        inputStream
                ?.bufferedReader()
                ?.readText()
                ?.apply { println(this) }
    }

fun Copy.hideFromGroup() {
    group = ""
}

fun DefaultTask.setTaskGroup() {
    group = "aws"
}

fun DefaultTask.setSubtaskGroup() {
    group = "aws-subtasks"
}

fun Project.registerDefaultTask(name: String,  configuration: DefaultTask.() -> Unit) =
        tasks.register<DefaultTask>(name).apply {
            configuration.invoke(get())
        }