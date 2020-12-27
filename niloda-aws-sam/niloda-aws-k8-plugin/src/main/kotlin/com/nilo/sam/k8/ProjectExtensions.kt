package com.nilo.sam.k8

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import java.io.File

inline fun <reified T:Any> Project.addTemplate(name: String) =
        extensions
                .create<T>(name)
                .apply{ findOrCreateConfig(name)}

fun Project.findOrCreateConfig(name: String) {
    if (configurations.findByName(name) == null) {
        configurations.create(name)
    }
}

fun Project.createExecTask(name: String, dir: String, command: String) {
    tasks.register<Exec>(name) {
        group = "aws"
        workingDir = File(dir)
        commandLine = listOf( "$dir/$command")
    }
}

fun Project.createEmptyTask(name: String, groupId: String): DefaultTask =
        registerDefaultTask(name) { group = groupId }.get()

fun Project.registerDefaultTask(name: String,  configuration: DefaultTask.() -> Unit) =
        tasks.register<DefaultTask>(name).apply {
            configuration.invoke(get())
        }