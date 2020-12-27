package com.nilo.sam.k8.cloudformation.cluster

object FormatSubnetRouteTableAssociations {
    operator fun invoke(publicSubnetOne: String, publicRouteTable: String, publicSubnetTwo: String) =
            """ |  ${publicSubnetOne}RouteTableAssociation:
                |    Type: AWS::EC2::SubnetRouteTableAssociation
                |    Properties:
                |      SubnetId: !Ref $publicSubnetOne
                |      RouteTableId: !Ref $publicRouteTable
                |  ${publicSubnetTwo}RouteTableAssociation:
                |    Type: AWS::EC2::SubnetRouteTableAssociation
                |    Properties:
                |      SubnetId: !Ref $publicSubnetTwo
                |      RouteTableId: !Ref $publicRouteTable"""
}