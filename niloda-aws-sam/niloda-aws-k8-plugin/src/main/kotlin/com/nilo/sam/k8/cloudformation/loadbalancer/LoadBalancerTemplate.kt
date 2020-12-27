package com.nilo.sam.k8.cloudformation.loadbalancer

import com.nilo.sam.k8.cloudformation.FormatDescription
import com.nilo.sam.k8.cloudformation.FormatResourcesHeader
import com.nilo.sam.k8.cloudformation.FormatTemplateVersion
import com.nilo.sam.k8.cloudformation.Ref
import com.nilo.sam.k8.cloudformation.cluster.FormatOutput
import com.nilo.sam.k8.cloudformation.cluster.FormatOutputsHeader

open class LoadBalancerTemplate {

    var enabled = true
    var lbEnvironment = "loadbalancer-test"
    var vpcEnvironment = "vpc-test"
    var publicSubnetOne = "PublicSubnetOne"
    var publicSubnetTwo = "PublicSubnetTwo"
    var ecsSecurityGroupIngressFromPublicAlb = "EcsSecurityGroupIngressFromPublicALB"
    var publicLoadBalancerSG = "PublicLoadBalancerSG"
    var vpcId = "VpcId"
    var containerSecurityGroup = "ContainerSecurityGroup"
    var dummyTargetGroupPublic = "DummyTargetGroupPublic"
    val port = "80"
    val protocol = "HTTP"
    val publicLoadBalancer = "PublicLoadBalancer"
    val dummyTargetGroupPublic1 = "DummyTargetGroupPublic"
    val publicLoadBalancerListener = "PublicLoadBalancerListener"
    val publicListenerOutputValue = Ref(publicLoadBalancerListener)
    val publicListenerOutputDescription = "The ARN of the public load balancer's Listener"
    val publicListener = "PublicListener"
    val externalUrlOutputValue = "!Sub http://${"$"}{PublicLoadBalancer.DNSName}"
    val externalUrlOutputDescription = "The url of the external load balancer"
    val externalUrl = "ExternalUrl"


    operator fun invoke(): String = """ 
    |${FormatTemplateVersion()}
    |${formatDescription()}
    |${FormatResourcesHeader()}
    |${formatEcsSecurityGroupIngress()}
    |${formatLoadBalanceSecurityGroup()}
    |${formatPublicLoadBalancer()}
    |${formatDummyTargetGroupPublic()}
    |${formatPublicLoadBalanceListener()}
    |${FormatOutputsHeader()}
    |${formatPublicListenerOutput()}
    |${formatExternalUrlOutput()}
        """.trimMargin()

    private fun formatDescription() =
            FormatDescription(description = "External, public facing load balancer, for forwarding public traffic to containers")

    private fun formatExternalUrlOutput() =
            FormatOutput(
                    value = externalUrlOutputValue,
                    environment = lbEnvironment,
                    description = externalUrlOutputDescription,
                    resourceName = externalUrl
            )

    private fun formatPublicListenerOutput() =
            FormatOutput(
                    value = publicListenerOutputValue,
                    environment = lbEnvironment,
                    description = publicListenerOutputDescription,
                    resourceName = publicListener
            )

    private fun formatPublicLoadBalanceListener() =
            FormatPublicLoadBalanceListener(
                    port = port,
                    protocol = protocol,
                    publicLoadBalancer = publicLoadBalancer,
                    dummyTargetGroupPublic = dummyTargetGroupPublic1,
                    publicLoadBalancerListener = publicLoadBalancerListener
            )

    private fun formatDummyTargetGroupPublic() =
            FormatDummyTargetGroupPublic(
                    vpcId = vpcId,
                    vpcEnvironment = vpcEnvironment,
                    dummyTargetGroupPublic = dummyTargetGroupPublic
            )

    private fun formatPublicLoadBalancer() =
            FormatPublicLoadBalancer(
                    vpcEnvironment = vpcEnvironment,
                    publicSubnetOne = publicSubnetOne,
                    publicSubnetTwo = publicSubnetTwo,
                    publicLoadBalancerSG = publicLoadBalancerSG
            )

    private fun formatLoadBalanceSecurityGroup() =
            FormatLoadBalanceSecurityGroup(
                    vpcId = vpcId,
                    vpcEnvironment = vpcEnvironment,
                    publicLoadBalancerSG = publicLoadBalancerSG
            )

    private fun formatEcsSecurityGroupIngress() =
            FormatEcsSecurityGroupIngress(
                    vpcEnvironment = vpcEnvironment,
                    publicLoadBalancerSG = publicLoadBalancerSG,
                    containerSecurityGroup = containerSecurityGroup,
                    ecsSecurityGroupIngressFromPublicAlb = ecsSecurityGroupIngressFromPublicAlb
            )
}