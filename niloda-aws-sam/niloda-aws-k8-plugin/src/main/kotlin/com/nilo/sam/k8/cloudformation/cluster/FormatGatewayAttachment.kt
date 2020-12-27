package com.nilo.sam.k8.cloudformation.cluster

object FormatGatewayAttachment {
    operator fun invoke(gatewayAttachment: String, vpc: String, internetGateway: String) =
            """ |  $gatewayAttachment:
                |    Type: AWS::EC2::VPCGatewayAttachment
                |    Properties:
                |      VpcId: !Ref '$vpc'
                |      InternetGatewayId: !Ref '$internetGateway'""".trimMargin()
}