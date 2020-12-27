package com.nilo.sam.k8.cloudformation.cluster

object FormatInternetGateway {
    operator fun invoke(internetGateway: String) =
            """ |  $internetGateway:
                |    Type: AWS::EC2::InternetGateway""".trimMargin()
}