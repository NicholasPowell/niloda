package com.nilo.sam.k8.cloudformation.service

import com.nilo.sam.k8.cloudformation.FormatResourcesHeader
import com.nilo.sam.k8.cloudformation.FormatTemplateVersion
import com.nilo.sam.k8.cloudformation.cluster.FormatOutput
import com.nilo.sam.k8.cloudformation.cluster.FormatOutputsHeader
import com.nilo.sam.k8.stripNonAlphaLower
import org.gradle.api.Project
import java.io.File

open class ServiceTemplate(

        var enabled:Boolean = true,
        var vpcEnvironment: String = "vpc-test",
        var lbEnvironment: String = "loadbalancer-test",
        var serviceName: String = "defaultService",
        var containerPort: String = "80",
        var containerCpu: String = "256",
        var containerMemory: String = "512",
        var path: String = "*",
        var priority: String = "1",
        var desiredCount: String = "1",
        var role: String = "",
        var awsAccountId: String = "168994756556",
        var region: String = "us-east-1",
        var imageFileLocation: String = "awsDigest.txt",
        var publicSubnetOne: String = "PublicSubnetOne",
        var publicSubnetTwo: String = "PublicSubnetTwo",
        var vpcId: String = "VpcId"

) {

    operator fun invoke(project: Project) =
            """
            |${FormatTemplateVersion()}
            |Description: Deploy a service on AWS Fargate, hosted in a public subnet, and accessible via a public load balancer.
            |
            |Conditions:
            |  HasCustomRole: !Not [ !Equals ['$role', ''] ]
            |
            |${FormatResourcesHeader()}
            |  # A log group for storing the stdout logs from this service's containers
            |  LogGroup:
            |    Type: AWS::Logs::LogGroup
            |    Properties:
            |      LogGroupName: ${vpcEnvironment}-service-$serviceName
            |
            |  # The task definition. This is a simple metadata description of what
            |  # container to run, and what resource requirements it has.
            |  TaskDefinition:
            |    Type: AWS::ECS::TaskDefinition
            |    Properties:
            |      Family: $serviceName
            |      Cpu: $containerCpu
            |      Memory: $containerMemory
            |      NetworkMode: awsvpc
            |      RequiresCompatibilities:
            |        - FARGATE
            |      ExecutionRoleArn:
            |        Fn::ImportValue: ${vpcEnvironment}:ECSTaskExecutionRole
            |      TaskRoleArn:
            |        Fn::If:
            |          - 'HasCustomRole'
            |          - "$role"
            |          - !Ref "AWS::NoValue"
            |      ContainerDefinitions:
            |        - Name: $serviceName
            |          Cpu: $containerCpu
            |          Memory: $containerMemory
            |          Image: ${readImageLocation(project)}
            |          PortMappings:
            |            - ContainerPort: $containerPort
            |          LogConfiguration:
            |            LogDriver: 'awslogs'
            |            Options:
            |              awslogs-group: ${vpcEnvironment}-service-$serviceName
            |              awslogs-region: !Ref 'AWS::Region'
            |              awslogs-stream-prefix: $serviceName
            |
            |  # The service. The service is a resource which allows you to run multiple
            |  # copies of a type of task, and gather up their logs and metrics, as well
            |  # as monitor the number of running tasks and replace any that have crashed
            |  Service:
            |    Type: AWS::ECS::Service
            |    DependsOn: LoadBalancerRule
            |    Properties:
            |      ServiceName: $serviceName
            |      Cluster:
            |        Fn::ImportValue: ${vpcEnvironment}:ClusterName
            |      LaunchType: FARGATE
            |      DeploymentConfiguration:
            |        MaximumPercent: 200
            |        MinimumHealthyPercent: 75
            |      DesiredCount: $desiredCount
            |      NetworkConfiguration:
            |        AwsvpcConfiguration:
            |          AssignPublicIp: ENABLED
            |          SecurityGroups:
            |            - Fn::ImportValue: ${vpcEnvironment}:ContainerSecurityGroup
            |          Subnets:
            |            - Fn::ImportValue: $vpcEnvironment:$publicSubnetOne
            |            - Fn::ImportValue: $vpcEnvironment:$publicSubnetTwo
            |      TaskDefinition: !Ref 'TaskDefinition'
            |      LoadBalancers:
            |        - ContainerName: $serviceName
            |          ContainerPort: $containerPort
            |          TargetGroupArn: !Ref 'TargetGroup'
            |
            |  # A target group. This is used for keeping track of all the tasks, and
            |  # what IP addresses / port numbers they have. You can query it yourself,
            |  # to use the addresses yourself, but most often this target group is just
            |  # connected to an application load balancer, or network load balancer, so
            |  # it can automatically distribute traffic across all the targets.
            |${FormatServiceTargetGroup(serviceName, containerPort, vpcEnvironment, vpcId)}
            |
            |  # Create a rule on the load balancer for routing traffic to the target group
            |  LoadBalancerRule:
            |    Type: AWS::ElasticLoadBalancingV2::ListenerRule
            |    Properties:
            |      Actions:
            |        - TargetGroupArn: !Ref 'TargetGroup'
            |          Type: 'forward'
            |      Conditions:
            |        - Field: path-pattern
            |          Values: ["$path"]
            |      ListenerArn:
            |        Fn::ImportValue: ${lbEnvironment}:PublicListener
            |      Priority: $priority
            |${FormatOutputsHeader()}
            |${FormatOutput("ExternalUrl", lbEnvironment, "\n      Fn::ImportValue: $lbEnvironment:ExternalUrl", "The url of the external load balancer", false)}
    """.trimMargin()

    private fun Project.getRepositoryUrl() =
            "$awsAccountId.dkr.ecr.$region.amazonaws.com/${name.stripNonAlphaLower()}:latest"

    private fun readImageLocation(project: Project) =
            File(imageFileLocation)
                    .let {
                        if (it.exists())
                            "${project.getRepositoryUrl()}@${it.inputStream().bufferedReader().readText()}"
                        else
                            project.getRepositoryUrl()
                    }
}
