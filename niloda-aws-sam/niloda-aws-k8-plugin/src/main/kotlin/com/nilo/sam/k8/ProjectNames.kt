package com.nilo.sam.k8

import org.gradle.api.Project

class ProjectNames(
        project: Project,
        val cluster: String = "${project.name}-cluster",
        val loadBalancer: String = "${project.name}-load-balancer",
        val service: String = "${project.name}-service",
        val repository: String = "${project.name}-repository"
)