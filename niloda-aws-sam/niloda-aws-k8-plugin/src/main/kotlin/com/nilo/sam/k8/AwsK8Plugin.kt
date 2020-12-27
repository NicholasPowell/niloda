package com.nilo.sam.k8

import com.nilo.sam.k8.cloudformation.GenerateArtifacts
import com.nilo.sam.k8.cloudformation.cluster.ClusterTemplate
import com.nilo.sam.k8.cloudformation.loadbalancer.LoadBalancerTemplate
import com.nilo.sam.k8.cloudformation.repository.RepositoryTemplate
import com.nilo.sam.k8.cloudformation.service.ServiceTemplate
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AwsK8Plugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {

            val names = ProjectNames(project)
            val generatedDir = "${buildDir}/generated/cloudformation"

            val templates = mutableMapOf<String, Pair<Boolean, () -> String>>()

            val loadBalancerTemplate = addTemplate<LoadBalancerTemplate>("loadBalancerTemplate")
            val serviceTemplate = addTemplate<ServiceTemplate>("serviceTemplate")
            val repositoryTemplate = addTemplate<RepositoryTemplate>("repositoryTemplate")

            afterEvaluate {

                templates[names.service] = serviceTemplate.enabled to { serviceTemplate(project) }
                templates[names.loadBalancer] = loadBalancerTemplate.enabled to { loadBalancerTemplate() }
                templates[names.repository] = repositoryTemplate.enabled to { repositoryTemplate(project) }

                GenerateArtifacts(
                        generatedDir,
                        templates
                                .filter { it.value.first }
                                .map { it.key to it.value.second() }
                                .toMap())

                if (repositoryTemplate.enabled)
                    createExecTask(repositoryTemplate.getBaseName(project) + "Repository", generatedDir, "${names.repository}.sh")

                if (serviceTemplate.enabled)
                    createExecTask(serviceTemplate.serviceName + "Service", generatedDir, "${names.service}.sh")

                if (loadBalancerTemplate.enabled)
                    createExecTask("deployLoadBalancer", generatedDir, "${names.loadBalancer}.sh")

            }
        }
    }

}

