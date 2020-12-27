package com.nilo.sam.k8.cloudformation.loadbalancer

object FormatDummyTargetGroupPublic {
    operator fun invoke(
            vpcId: String,
            vpcEnvironment: String,
            dummyTargetGroupPublic: String
    ) = """ |  # A dummy target group is used to setup the ALB to just drop traffic
            |  # initially, before any real service target groups have been added.
            |  $dummyTargetGroupPublic:
            |    Type: AWS::ElasticLoadBalancingV2::TargetGroup
            |    Properties:
            |      HealthCheckIntervalSeconds: 30
            |      HealthCheckPath: /
            |      HealthCheckProtocol: HTTP
            |      HealthCheckTimeoutSeconds: 15
            |      HealthyThresholdCount: 2
            |      Port: 80
            |      Protocol: HTTP
            |      UnhealthyThresholdCount: 2
            |      VpcId:
            |        Fn::ImportValue: $vpcEnvironment:$vpcId""".trimMargin()
}