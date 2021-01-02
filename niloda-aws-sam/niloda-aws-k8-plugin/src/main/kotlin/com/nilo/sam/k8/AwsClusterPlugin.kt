package com.nilo.sam.k8

import com.nilo.sam.k8.cloudformation.GenerateArtifacts
import com.nilo.sam.k8.cloudformation.cluster.ClusterTemplate
import com.nilo.sam.k8.cloudformation.loadbalancer.LoadBalancerTemplate
import com.nilo.sam.k8.cloudformation.repository.RepositoryTemplate
import com.nilo.sam.k8.cloudformation.service.ServiceTemplate
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AwsClusterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {

            val generatedDir = "${buildDir}/generated/cluster"

            val clusterTemplate = addTemplate<ClusterTemplate>("clusterTemplate")

            afterEvaluate {
                val clusterName = clusterTemplate.name
                GenerateArtifacts(
                        generatedDir,
                        mapOf( clusterName to clusterTemplate() )
                )

                if (clusterTemplate.enabled)
                    createExecTask("deployCluster", generatedDir, "$clusterName.sh")
            }
        }
    }

}

