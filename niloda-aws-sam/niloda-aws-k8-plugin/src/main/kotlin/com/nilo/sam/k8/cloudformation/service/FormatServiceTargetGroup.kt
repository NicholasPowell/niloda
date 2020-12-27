package com.nilo.sam.k8.cloudformation.service

object FormatServiceTargetGroup {
    operator fun invoke(
            serviceName: String,
            containerPort: String,
            vpcEnvironment: String,
            vpcId: String,
            targetGroup: String = "TargetGroup",
            healthCheckIntervalSeconds: String = "60",
            healthCheckTimeoutSeconds: String = "45",
            healthyThresholdCount: String = "2",
            protocol: String = "HTTP",
            unhealthyThresholdCount: String = "2"
    ) =
            """ |  $targetGroup:
                |    Type: AWS::ElasticLoadBalancingV2::TargetGroup
                |    Properties:
                |      HealthCheckIntervalSeconds: $healthCheckIntervalSeconds
                |      HealthCheckPath: /
                |      HealthCheckProtocol: HTTP
                |      HealthCheckTimeoutSeconds: $healthCheckTimeoutSeconds
                |      HealthyThresholdCount: $healthyThresholdCount
                |      TargetType: ip
                |      Name: $serviceName
                |      Port: $containerPort
                |      Protocol: $protocol
                |      UnhealthyThresholdCount: $unhealthyThresholdCount
                |      VpcId:
                |        Fn::ImportValue: ${vpcEnvironment}:$vpcId""".trimMargin()
}