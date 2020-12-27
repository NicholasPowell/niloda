package com.nilo.sam.k8.cloudformation.loadbalancer

object FormatPublicLoadBalancer {
    operator fun invoke(
            vpcEnvironment: String,
            publicSubnetOne: String,
            publicSubnetTwo: String,
            publicLoadBalancerSG: String,
            publicLoadBalancer: String = "PublicLoadBalancer",
            scheme: String = "internet-facing",
            idleTimeout: String = "30"

    ) =
            """ |  $publicLoadBalancer:
                |    Type: AWS::ElasticLoadBalancingV2::LoadBalancer
                |    Properties:
                |      Scheme: $scheme
                |      LoadBalancerAttributes:
                |      - Key: idle_timeout.timeout_seconds
                |        Value: '$idleTimeout'
                |      Subnets:
                |        # The load balancer is placed into the public subnets, so that traffic
                |        # from the internet can reach the load balancer directly via the internet gateway
                |        - Fn::ImportValue: $vpcEnvironment:$publicSubnetOne
                |        - Fn::ImportValue: $vpcEnvironment:$publicSubnetTwo
                |      SecurityGroups: [!Ref '$publicLoadBalancerSG']""".trimMargin()
}