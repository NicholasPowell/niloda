package com.nilo.sam.k8.cloudformation.loadbalancer

object FormatLoadBalanceSecurityGroup {
    operator fun invoke(publicLoadBalancerSG: String, vpcEnvironment: String, vpcId: String) =
    """
    |
    |  # Public load balancer, hosted in public subnets that is accessible
    |  # to the public, and is intended to route traffic to one or more public
    |  # facing services. This is used for accepting traffic from the public
    |  # internet and directing it to public facing microservices
    |  $publicLoadBalancerSG:
    |    Type: AWS::EC2::SecurityGroup
    |    Properties:
    |      GroupDescription: Access to the public facing load balancer
    |      VpcId:
    |        Fn::ImportValue: $vpcEnvironment:$vpcId
    |      SecurityGroupIngress:
    |          # Allow access to ALB from anywhere on the internet
    |          - CidrIp: 0.0.0.0/0
    |            IpProtocol: -1""".trimMargin()
}