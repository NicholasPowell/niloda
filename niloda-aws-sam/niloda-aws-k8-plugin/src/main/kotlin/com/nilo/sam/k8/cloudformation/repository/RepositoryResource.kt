package com.nilo.sam.k8.cloudformation.repository

import com.nilo.sam.k8.toUpperCamelCase

object RepositoryResource {
    operator fun invoke(name: String) = name.toUpperCamelCase() + "Repo"
}