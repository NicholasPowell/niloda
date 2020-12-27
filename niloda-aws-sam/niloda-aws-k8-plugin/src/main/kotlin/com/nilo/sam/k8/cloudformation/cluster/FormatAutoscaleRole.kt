package com.nilo.sam.k8.cloudformation.cluster

object FormatAutoscaleRole {
    operator fun invoke(
            autoscalingRole: String,
            policyName: String = "service-autoscaling"
    ) =
            """ |  $autoscalingRole:
                |    Type: AWS::IAM::Role
                |    Properties:
                |      AssumeRolePolicyDocument:
                |        Statement:
                |        - Effect: Allow
                |          Principal:
                |            Service: [application-autoscaling.amazonaws.com]
                |          Action: ['sts:AssumeRole']
                |      Path: /
                |      Policies:
                |      - PolicyName: $policyName
                |        PolicyDocument:
                |          Statement:
                |          - Effect: Allow
                |            Action:
                |              - 'application-autoscaling:*'
                |              - 'cloudwatch:DescribeAlarms'
                |              - 'cloudwatch:PutMetricAlarm'
                |              - 'ecs:DescribeServices'
                |              - 'ecs:UpdateService'
                |            Resource: '*'""".trimMargin()
}