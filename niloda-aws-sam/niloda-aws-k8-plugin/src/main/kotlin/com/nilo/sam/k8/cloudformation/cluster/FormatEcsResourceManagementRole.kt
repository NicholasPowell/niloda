package com.nilo.sam.k8.cloudformation.cluster

object FormatEcsResourceManagementRole {
    operator fun invoke(
            ecsRole: String,
            policyName: String = "ecs-service"
    ) =
            """ |  $ecsRole:
                |    Type: AWS::IAM::Role
                |    Properties:
                |      AssumeRolePolicyDocument:
                |        Statement:
                |        - Effect: Allow
                |          Principal:
                |            Service: [ecs.amazonaws.com]
                |          Action: ['sts:AssumeRole']
                |      Path: /
                |      Policies:
                |      - PolicyName: $policyName
                |        PolicyDocument:
                |          Statement:
                |          - Effect: Allow
                |            Action:
                |              # Rules which allow ECS to attach network interfaces to instances
                |              # on your behalf in order for awsvpc networking mode to work right
                |              - 'ec2:AttachNetworkInterface'
                |              - 'ec2:CreateNetworkInterface'
                |              - 'ec2:CreateNetworkInterfacePermission'
                |              - 'ec2:DeleteNetworkInterface'
                |              - 'ec2:DeleteNetworkInterfacePermission'
                |              - 'ec2:Describe*'
                |              - 'ec2:DetachNetworkInterface'
                |
                |              # Rules which allow ECS to update load balancers on your behalf
                |              # with the information sabout how to send traffic to your containers
                |              - 'elasticloadbalancing:DeregisterInstancesFromLoadBalancer'
                |              - 'elasticloadbalancing:DeregisterTargets'
                |              - 'elasticloadbalancing:Describe*'
                |              - 'elasticloadbalancing:RegisterInstancesWithLoadBalancer'
                |              - 'elasticloadbalancing:RegisterTargets'
                |            Resource: '*' """.trimMargin()
}