package com.nilo.sam.lambda.cloudformation


class LambdaLayersResource(val layers: List<LambdaLayer>) {
    fun getLayers() = layers.joinToString(",") { it.getLayer() }
    fun getReferences() = layers.joinToString(",") { it.getReference() }
}