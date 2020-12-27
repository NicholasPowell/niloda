package com.nilo.sam.k8.cloudformation.cluster

object FormatSubnetConfigCidrs {
    operator fun invoke(vpcCidr: String, publicOneCidr: String, publicTwoCidr: String, publicOne: String, publicTwo: String, vpc: String) =
            """ |  SubnetConfig:
                |    $vpc:
                |      CIDR: '$vpcCidr'
                |    $publicOne:
                |      CIDR: '$publicOneCidr'
                |    $publicTwo:
                |      CIDR: '$publicTwoCidr'""".trimMargin()
}