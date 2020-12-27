package com.nilo.dependencies

object NiloServerlessStarter {

    val webflux = NiloStarter.webflux +
            listOf(
                    Aws.lambdaSdk,
                    Aws.apiGatewaySdk,
                    Aws.lambdaCore,
                    Aws.lambdaEvents,
                    SpringCloudFunction.kotlin,
                    SpringCloudFunction.web,
                    Aws.serverlessJavaContainerSpringBoot
            )
}