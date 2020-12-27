package com.nilo.sam.k8.cloudformation.cluster

object FormatContainerSecurityGroup {
    operator fun invoke(
            vpc: String,
            containerSecurityGroup: String,
            groupDescription: String = "Access to the Fargate containers"
    ) =
            """ |  $containerSecurityGroup:
                |    Type: AWS::EC2::SecurityGroup
                |    Properties:
                |      GroupDescription: $groupDescription
                |      VpcId: !Ref '$vpc'""".trimMargin()
}