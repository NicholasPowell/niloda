package com.nilo.sam.k8.cloudformation.cluster

object FormatVpc {
    operator fun invoke(vpc: String) =
        """ |  $vpc:
            |    Type: AWS::EC2::VPC
            |    Properties:
            |      EnableDnsSupport: true
            |      EnableDnsHostnames: true
            |      CidrBlock: !FindInMap ['SubnetConfig', '$vpc', 'CIDR']""".trimMargin()
}