package com.nilo.dependencies

@Suppress("unused")
object NiloDependencyLayer {
    val all =
            listOf(
                    SpringCloudFunction.web,
                    SpringCloudFunction.kotlin,
                    SpringCloudFunctionAdapter.web,
                    SpringCloudFunctionAdapter.aws,
                    Aws.WithVersion.lambdaSdk,
                    Aws.WithVersion.lambdaCore,
                    Aws.WithVersion.dynamoClient,
                    Aws.WithVersion.lambdaEvents,
                    Aws.WithVersion.apiGatewaySdk,
                    Aws.WithVersion.serverlessJavaContainerSpringBoot,
                    Kotlin.WithVersion.sdk1_8,
                    Kotlin.WithVersion.logging,
                    Jackson.WithVersion.moduleKotlin,
                    SpringBoot.WithVersion.starterWebflux
            )

    val platform = listOf(
            SpringBoot.platform,
            SpringCloud.platform
    )
}