package com.nilo.sam.k8.cloudformation

object Ref { operator fun invoke(name: String) = "!Ref '$name'" }