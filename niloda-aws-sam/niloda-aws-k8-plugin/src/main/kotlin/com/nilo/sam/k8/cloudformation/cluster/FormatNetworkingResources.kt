package com.nilo.sam.k8.cloudformation.cluster

object FormatNetworkingResources {
    operator fun invoke(
            publicSubnetOne: String,
            publicSubnetTwo: String,
            vpc: String,
            publicRouteTable: String = "PublicRouteTable",
            publicRoute: String = "PublicRoute",
            internetGateway: String = "InternetGateway",
            gatewayAttachment: String = "GatewayAttachment"
    ) =
            """ |${FormatInternetGateway(internetGateway)}
                |${FormatGatewayAttachment(gatewayAttachment, vpc, internetGateway)}
                |${FormatPublicRouteTable(publicRouteTable, vpc)}
                |${FormatPublicRoute(publicRoute, gatewayAttachment, publicRouteTable, internetGateway)}
                |${FormatSubnetRouteTableAssociations(publicSubnetOne, publicRouteTable, publicSubnetTwo)}""".trimMargin()

}