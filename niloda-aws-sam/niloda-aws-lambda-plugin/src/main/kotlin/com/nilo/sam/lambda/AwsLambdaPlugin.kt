package com.nilo.sam.lambda

import com.nilo.sam.lambda.cloudformation.MultiLambdaApi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import java.io.File
import java.util.concurrent.TimeUnit

@Suppress("unused")
class AwsLambdaPlugin : Plugin<Project> {

    private val withoutPerms = "withoutPerms.json"
    private val withPerms = "withPerms.json"

    override fun apply(project: Project) {
        with(project) {
            val extension = project.extensions.create<AwsSamPluginExtension>("functions")
            registerDoLastTask("createSamTemplates", "aws") { writeSamTemplates(extension) }
            registerDoLastTask("uploadSamApp", "aws") { executeSamDeploy() }
        }
    }
    private fun writeSamTemplates(extension: AwsSamPluginExtension) {
        val workaround = listOf(true, false)
        workaround.forEach { firstTime ->
            createTemplateJson(firstTime, extension)
            createScript(firstTime)
        }
    }

    private fun createTemplateJson(firstTime: Boolean, extension: AwsSamPluginExtension) {
        File(if (firstTime) withoutPerms else withPerms).printWriter().use { file ->
            file.println(
                    MultiLambdaApi(
                            names = extension.functionsToDeploy,
                            firstTime = firstTime,
                            codeUri = extension.codeUri,
                            lambdaLayers = extension.layers
                    ).format()
            )
        }
    }

    private fun executeSamDeploy() {
        val start = System.currentTimeMillis()
        listOf(withoutPerms, withPerms)
                .forEach {
                    """sam deploy --template-file $it --stack-name skipmo --capabilities CAPABILITY_IAM --s3-bucket com.nilo.rock;
    """.trimIndent().runCommand(File("."))?.printResults()
                }
        println("ExecuteSamDeploy took: ${TimeUnit.SECONDS.convert(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS)}")

    }

    private fun createScript(firstTime: Boolean) {
        File((if (firstTime) withoutPerms else withPerms).replace(".json", ".sh")).printWriter().use {
            it.println(
                    """
                    #!/bin/bash
                    sam deploy --template-file ${if (firstTime) withoutPerms else withPerms} --stack-name skipmo --capabilities CAPABILITY_IAM --s3-bucket com.nilo.rock
                """.trimIndent()
            )
        }
    }

}

