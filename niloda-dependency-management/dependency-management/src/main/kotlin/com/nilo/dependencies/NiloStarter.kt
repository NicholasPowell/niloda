package com.nilo.dependencies

object NiloStarter {
    val webflux =
            listOf(
                    SpringBoot.starterParent,
                    SpringBoot.starterWebflux,
                    SpringBoot.actuator,
                    Kotlin.logging,
                    Jackson.moduleKotlin,
                    Jackson.core
                )
}