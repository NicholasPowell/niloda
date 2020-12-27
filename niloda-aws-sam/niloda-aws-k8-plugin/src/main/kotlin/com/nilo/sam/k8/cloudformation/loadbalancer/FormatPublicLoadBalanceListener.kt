package com.nilo.sam.k8.cloudformation.loadbalancer

import com.nilo.sam.k8.cloudformation.Ref

object FormatPublicLoadBalanceListener {
    operator fun invoke(
            port: String,
            protocol: String,
            publicLoadBalancer: String,
            dummyTargetGroupPublic: String,
            publicLoadBalancerListener: String
    ) =
            """ |  $publicLoadBalancerListener:
                |    Type: AWS::ElasticLoadBalancingV2::Listener
                |    DependsOn:
                |      - $publicLoadBalancer
                |    Properties:
                |      DefaultActions:
                |        - TargetGroupArn: ${Ref(dummyTargetGroupPublic)}
                |          Type: 'forward'
                |      LoadBalancerArn: ${Ref(publicLoadBalancer)}
                |      Port: $port
                |      Protocol: $protocol""".trimMargin()
}