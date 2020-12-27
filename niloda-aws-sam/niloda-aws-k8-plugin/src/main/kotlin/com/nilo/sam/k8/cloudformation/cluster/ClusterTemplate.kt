package com.nilo.sam.k8.cloudformation.cluster

import com.nilo.sam.k8.cloudformation.*

open class ClusterTemplate {

    var name = "public-cluster"

    var enabled = false
    var vpcEnvironment = "vpc-test"

    var vpcCidr = "10.3.0.0/16"
    var publicOneCidr = "10.3.0.0/24"
    var publicTwoCidr = "10.3.1.0/24"

    var description = "AWS Fargate cluster running containers in a public subnet. Only supports public facing load balancer, and public service discovery namespaces."

    var publicOne = "PublicOne"
    var publicTwo = "PublicTwo"
    var publicSubnetOne = "PublicSubnetOne"
    var publicSubnetTwo = "PublicSubnetTwo"
    var vpc = "VPC"
    var ecsRole = "ECSRole"
    var ecsTaskExecutableRole = "ECSTaskExecutionRole"
    var ecsCluster = "ECSCluster"
    var autoscalingRole = "AutoscalingRole"
    var clusterName = "ClusterName"
    var vpcId = "VpcId"
    var containerSecurityGroup = "ContainerSecurityGroup"

    operator fun invoke(): String {
        return """  |${FormatTemplateVersion()}
                    |${FormatDescription(description)}
                    |${FormatMappingsHeader()}
                    |${FormatSubnetConfigCidrs(vpcCidr, publicOneCidr, publicTwoCidr, publicOne, publicTwo, vpc)}
                    |${FormatResourcesHeader()}
                    |${FormatVpc(vpc)}
                    |${FormatDistributedPublicSubnets(vpc, publicSubnetOne, publicSubnetTwo)}
                    |${FormatNetworkingResources(publicSubnetOne, publicSubnetTwo, vpc)}
                    |${FormatEcsCluster(ecsCluster)}
                    |${FormatContainerSecurityGroup(vpc, containerSecurityGroup)}
                    |${FormatAutoscaleRole(autoscalingRole)}
                    |${FormatEcsResourceManagementRole(ecsRole)}
                    |${FormatEcsTaskRole(ecsTaskExecutableRole)}
                    |${FormatOutputsHeader()}
                    |${FormatOutput(vpcId, vpcEnvironment, Ref(vpc))}
                    |${FormatOutput(ecsRole, vpcEnvironment, GetAtt(ecsRole))}
                    |${FormatOutput(clusterName, vpcEnvironment, Ref(ecsCluster))}
                    |${FormatOutput(autoscalingRole, vpcEnvironment, GetAtt(autoscalingRole))}
                    |${FormatOutput(publicSubnetOne, vpcEnvironment)}
                    |${FormatOutput(publicSubnetTwo, vpcEnvironment)}
                    |${FormatOutput(ecsTaskExecutableRole, vpcEnvironment, GetAtt(ecsTaskExecutableRole))}
                    |${FormatOutput(containerSecurityGroup, vpcEnvironment)}
                """.trimMargin()
    }
}