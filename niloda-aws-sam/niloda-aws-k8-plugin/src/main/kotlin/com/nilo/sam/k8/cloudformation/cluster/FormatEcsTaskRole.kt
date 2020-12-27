package com.nilo.sam.k8.cloudformation.cluster

object FormatEcsTaskRole {
    operator fun invoke(ecsTaskExecutableRole: String) =
            """ |  $ecsTaskExecutableRole:
                |    Type: AWS::IAM::Role
                |    Properties:
                |      AssumeRolePolicyDocument:
                |        Statement:
                |        - Effect: Allow
                |          Principal:
                |            Service: [ecs-tasks.amazonaws.com]
                |          Action: ['sts:AssumeRole']
                |      Path: /
                |      Policies:
                |        - PolicyName: AmazonECSTaskExecutionRolePolicy
                |          PolicyDocument:
                |            Statement:
                |            - Effect: Allow
                |              Action:
                |                # Allow the ECS Tasks to download images from ECR
                |                - 'ecr:GetAuthorizationToken'
                |                - 'ecr:BatchCheckLayerAvailability'
                |                - 'ecr:GetDownloadUrlForLayer'
                |                - 'ecr:BatchGetImage'
                |
                |                # Allow the ECS tasks to upload logs to CloudWatch
                |                - 'logs:CreateLogStream'
                |                - 'logs:PutLogEvents'
                |              Resource: '*'""".trimMargin()
}