package com.nilo.sam.k8.cloudformation

import com.nilo.sam.k8.writeContents
import java.io.File

object WriteDeployScripts {

    operator fun invoke(
            generatedDir: String,
            formations: List<CloudFormationArtifact>

    ) {
        formations
                .map { "$generatedDir/${it.script}" to formatDeployScript(it.args) }
                .forEach { (file, contents) ->
                    file.writeContents(contents)
                    File(file).setExecutable(true)
                }
    }

    private fun formatDeployScript(args: String) =
            "sam deploy $args"
}

