package com.nilo.dependencies

object Aws {

    val lambdaSdk = WithoutVersion.lambdaSdk
    val lambdaCore = WithoutVersion.lambdaCore
    val lambdaEvents = WithoutVersion.lambdaEvents
    val dynamoClient = WithoutVersion.dynamoClient
    val apiGatewaySdk = WithoutVersion.apiGatewaySdk
    val serverlessJavaContainerSpringBoot = WithoutVersion.serverlessJavaContainerSpringBoot

    object WithVersion {
        const val lambdaSdk = "com.amazonaws:aws-java-sdk-lambda:1.11.860"
        const val lambdaCore = "com.amazonaws:aws-lambda-java-core:1.2.0"
        const val lambdaEvents = "com.amazonaws:aws-lambda-java-events:2.0.2"
        const val dynamoClient = "com.amazonaws:aws-java-sdk-dynamodb:1.11.847"
        const val apiGatewaySdk = "com.amazonaws:aws-java-sdk-api-gateway:1.11.862"
        const val serverlessJavaContainerSpringBoot = "com.amazonaws.serverless:aws-serverless-java-container-springboot2:1.5.1"
    }
    object WithoutVersion {
        val lambdaSdk = WithVersion.lambdaSdk.stripVersion()
        val lambdaCore = WithVersion.lambdaCore.stripVersion()
        val lambdaEvents = WithVersion.lambdaEvents.stripVersion()
        val dynamoClient = WithVersion.dynamoClient.stripVersion()
        val apiGatewaySdk = WithVersion.apiGatewaySdk.stripVersion()
        val serverlessJavaContainerSpringBoot = WithVersion.serverlessJavaContainerSpringBoot.stripVersion()
    }
}