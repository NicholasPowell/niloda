package com.nilo.sam.lambda

import com.nilo.sam.lambda.cloudformation.LambdaLayersResource

open class AwsSamPluginExtension {
    var functionsToDeploy: MutableList<String> = mutableListOf()
    var codeUri: String = "/Users/nickpowell/projects/skipmo/cloudfunctions/persistence/build/libs/persistence-all.jar"
    var layers: LambdaLayersResource = LambdaLayersResource(listOf())
}