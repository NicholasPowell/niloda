package com.nilo.sam.k8.cloudformation.repository

import com.nilo.sam.k8.stripNonAlphaLower
import com.nilo.sam.k8.toUpperCamelCase
import org.gradle.api.Project

open class RepositoryTemplate {

    var enabled = true
    var vpcEnvironment = "vpc-test"
    var repositoryBaseName = ""

    operator fun invoke(project: Project): String {

        val baseName = getBaseName(project)
        val repositoryResource = baseName.toUpperCamelCase() + "Repo"
        val repositoryName = baseName.stripNonAlphaLower()

        return """  |AWSTemplateFormatVersion: '2010-09-09'
                    |Description: ECR Repository
                    |Resources: 
                    |${FormatRepositoryResource(repositoryResource, repositoryName)}
                    |Outputs:
                    |${FormatRepositoryOutput(repositoryResource, vpcEnvironment)} 
                """.trimMargin()
    }

    fun getBaseName(project: Project) =
            if (repositoryBaseName.isNotEmpty())
                repositoryBaseName
            else
                project.name
}