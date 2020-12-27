package com.nilo.sam.k8.cloudformation.cluster

object FormatOutput {
    operator fun invoke(
            resourceName: String,
            environment: String,
            value: String = "!Ref '$resourceName'",
            description: String = "$environment $resourceName output",
            export: Boolean = true
    ) =
            """ |  $resourceName:
                |    Description: $description
                |    Value: $value
                |${FormatExport(export, environment, resourceName)}""".trimMargin()

    fun FormatExport(export: Boolean, environment: String, resourceName: String) =
            if (export) """ 
                |    Export:
                |      Name: $environment:$resourceName""".trimMargin()
            else ""
}