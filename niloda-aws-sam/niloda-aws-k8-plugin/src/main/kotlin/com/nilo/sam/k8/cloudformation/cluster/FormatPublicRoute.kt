package com.nilo.sam.k8.cloudformation.cluster

object FormatPublicRoute {
    operator fun invoke(publicRoute: String, gatewayAttachment: String, publicRouteTable: String, internetGateway: String) =
        """ |  $publicRoute:
            |    Type: AWS::EC2::Route
            |    DependsOn: $gatewayAttachment
            |    Properties:
            |      RouteTableId: !Ref '$publicRouteTable'
            |      DestinationCidrBlock: '0.0.0.0/0'
            |      GatewayId: !Ref '$internetGateway'"""
}