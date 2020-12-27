package com.nilo.sam.lambda

import org.gradle.api.Project

fun Project.registerDoLastTask(name: String, groupId: String, action: ()->Unit ) {
    project.tasks.register(name) {
        group = groupId
        doLast {
            action
        }
    }
}

fun Process.printResults() {
    inputStream?.bufferedReader()?.readText()?.apply { println(this) }
}
