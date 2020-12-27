package com.nilo.sam.k8.cloudformation.loadbalancer

object FormatEcsSecurityGroupIngress {
    operator fun invoke(
            vpcEnvironment: String,
            publicLoadBalancerSG: String,
            containerSecurityGroup: String,
            ecsSecurityGroupIngressFromPublicAlb: String
    ) = """ |  $ecsSecurityGroupIngressFromPublicAlb:
            |    Type: AWS::EC2::SecurityGroupIngress
            |    Properties:
            |      Description: Ingress from the public ALB
            |      GroupId:
            |        Fn::ImportValue: $vpcEnvironment:$containerSecurityGroup
            |      IpProtocol: -1
            |      SourceSecurityGroupId: !Ref '$publicLoadBalancerSG'""".trimMargin()
}