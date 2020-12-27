package com.nilo.sam.k8.cloudformation.cluster

object FormatEcsCluster {
    operator fun invoke(ecsCluster: String) =
            """ |  $ecsCluster:
                |    Type: AWS::ECS::Cluster""".trimMargin()
}