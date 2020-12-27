package com.nilo.sam.lambda.cloudformation

import java.io.File

class LambdaLayer(
        private val layerName: String,
        private val dependencyLib: File
) {
    fun getLayer() = """
        "$layerName" : {
            "Type": "AWS::Serverless::LayerVersion",
            "Properties": {
                "LayerName": "$layerName",
                "Description": "Dependencies for lambda functions",
                "ContentUri": "${getContentUri()}",
                "CompatibleRuntimes": [ "java11" ],
                "LicenseInfo": "MIT",
                "RetentionPolicy": "Retain"
            }
        },
    """.trimIndent()

    fun getReference() = """ {"Ref" : "$layerName" } """

    private fun getContentUri() = dependencyLib.absolutePath
}