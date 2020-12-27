package com.nilo.sam.k8.cloudformation.cluster

object FormatPublicRouteTable {
    operator fun invoke(publicRouteTable: String, vpc: String) =
            """ |  $publicRouteTable:
                |    Type: AWS::EC2::RouteTable
                |    Properties:
                |      VpcId: !Ref '$vpc'"""
}