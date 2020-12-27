package com.nilo.sam.k8.cloudformation.cluster

object FormatDistributedPublicSubnets {
    operator fun invoke(vpc: String, publicSubnetOne: String, publicSubnetTwo: String) =
            """ |  # Two public subnets, where containers can have public IP addresses
                |  $publicSubnetOne:
                |    Type: AWS::EC2::Subnet
                |    Properties:
                |      AvailabilityZone:
                |         Fn::Select:
                |         - 0
                |         - Fn::GetAZs: {Ref: 'AWS::Region'}
                |      VpcId: !Ref '$vpc'
                |      CidrBlock: !FindInMap ['SubnetConfig', 'PublicOne', 'CIDR']
                |      MapPublicIpOnLaunch: true
                |  $publicSubnetTwo:
                |    Type: AWS::EC2::Subnet
                |    Properties:
                |      AvailabilityZone:
                |         Fn::Select:
                |         - 1
                |         - Fn::GetAZs: {Ref: 'AWS::Region'}
                |      VpcId: !Ref '$vpc'
                |      CidrBlock: !FindInMap ['SubnetConfig', 'PublicTwo', 'CIDR']
                |      MapPublicIpOnLaunch: true """.trimMargin()
}