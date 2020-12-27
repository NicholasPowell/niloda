package com.nilo.sam.k8.cloudformation.repository

import com.nilo.sam.k8.stripNonAlphaLower

object RepositoryName {
    operator fun invoke(name: String) = name.toLowerCase().stripNonAlphaLower()
}

