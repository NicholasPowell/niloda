package com.nilo.sam.k8.cloudformation

class CloudFormationArtifact(
        val name: String,
        val script: String = "$name.sh",
        private val stack: String = "$name-stack",
        private val template: String = "$name.yml",
        private val parameters: Map<String, String> = mapOf(),
        private val capabilities: Map<String, String> = mapOf()
) {

    val args = formatArgs()

    private fun formatArgs(): String =
            listOf(
                    formatTemplateFile(),
                    formatStackName(),
                    formatCapabilitiesArg(),
                    parameters.formatIfPresent { formatParametersArg() },
                    formatNoFail()
            )
                    .joinToString(" \\\n")

    private fun formatNoFail() = "--no-fail-on-empty-changeset"

    private fun formatStackName() = "--stack-name $stack"

    private fun Map<String, String>.formatIfPresent(format: () -> String) =
            if (isNotEmpty()) format()
            else ""

    private fun formatTemplateFile() = "--template-file $template"

    private fun formatCapabilitiesArg() = "--capabilities CAPABILITY_IAM"

    private fun formatParametersArg() = "--parameter-overrides ${formatParamsKeyEqValue()}"

    private fun formatParamsKeyEqValue() = parameters.entries.joinToString(" ") { formatKeyEqValue(it) }

    private fun formatKeyEqValue(it: Map.Entry<String, String>) = "${it.key}=${it.value}"
}
