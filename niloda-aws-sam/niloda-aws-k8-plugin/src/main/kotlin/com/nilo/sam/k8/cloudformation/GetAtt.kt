package com.nilo.sam.k8.cloudformation

object GetAtt { operator fun invoke(name: String, att: String = "Arn") = "!GetAtt '$name.$att'" }