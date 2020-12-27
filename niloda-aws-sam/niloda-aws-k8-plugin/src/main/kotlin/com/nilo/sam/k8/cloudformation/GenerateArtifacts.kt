package com.nilo.sam.k8.cloudformation

import java.io.File

object GenerateArtifacts {
    operator fun invoke(dir: String, templateToYml: Map<String, String>) {
        cleanDir(dir)
        WriteYmls(dir, templateToYml.map { FileToContents(it.key, it.value) })
        WriteDeployScripts(dir, templateToYml.map { CloudFormationArtifact(it.key) })
    }

    private fun cleanDir(dir: String) {
        File(dir).apply {
            deleteRecursively()
            mkdir()
        }
    }
}