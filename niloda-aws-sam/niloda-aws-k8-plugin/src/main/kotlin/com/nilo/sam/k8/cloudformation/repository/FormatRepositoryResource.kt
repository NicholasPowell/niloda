package com.nilo.sam.k8.cloudformation.repository

object FormatRepositoryResource {
    operator fun invoke(repositoryResource: String, repositoryName: String) =
            """ |  $repositoryResource:
                |     Type: AWS::ECR::Repository
                |     Properties:
                |       RepositoryName: $repositoryName   
            """.trimMargin()
}



