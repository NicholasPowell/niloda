package com.nilo.sam.k8.cloudformation.repository

import com.nilo.sam.k8.cloudformation.GetAtt

object FormatRepositoryOutput {
    operator fun invoke(repositoryResource: String, environment: String) =
            """ |  $repositoryResource:
                |    Value: ${GetAtt(repositoryResource)}
                |    Export:
                |      Name: $environment:$repositoryResource
            """.trimMargin()
}
