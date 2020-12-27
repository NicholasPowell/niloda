package com.nilo.sam.lambda.cloudformation

class LambdaWithApiResource(val names: List<String>,
                            val firstTime: Boolean,
                            val codeUri: String,
                            val lambdaLayers: LambdaLayersResource
) {

    fun getResources() = lambdaLayers.getLayers() + getResourceForEachName()

    private fun getResourceForEachName() = names.joinToString(",") { getResource(it) }

    private fun getResource(name: String, path: String = name.toLowerCase()) = """
        "$name": {
             "Type": "AWS::Serverless::Function",
             "Properties": {
                 "Layers": [ ${lambdaLayers.getReferences()} ],
                 "AutoPublishAlias": { "Ref": "AutoPublishAliasName" },
                 "FunctionName": { "Fn::Sub": "$name-${'$'}{StageName}" },
                 "Handler": "${getHandler()}",
                 "Runtime": "${getRuntime()}",
                 "CodeUri": "$codeUri",
                 "Environment": {
                     "Variables": { ${getEnvVars(path)} }
                 },
                 "Role": { "Fn::GetAtt": [ "${getIamRole(name)}", "Arn" ] },
                 "Events": {
                     "AnyRequest": {
                         "Type": "Api",
                         "Properties": {
                             "Path": "/",
                             "Method": "POST",
                             "RestApiId": {
                                 "Ref": "${name}API"
                             }
                         }
                     }
                 },
                 "Timeout": 90,
                 "MemorySize": 512,
                 "ProvisionedConcurrencyConfig": {
                     "ProvisionedConcurrentExecutions": 1
                 },
                 "DeploymentPreference": {
                     "Type": "AllAtOnce"
                 }
             }
         },
         ${if(firstTime) "" else """
             "${name}APILambdaPermission": {
                 "DependsOn": "$name",
                 "Type": "AWS::Lambda::Permission",
                 "Properties": {
                     "Action": "lambda:InvokeFunction",
                     "SourceArn": {
                         "Fn::Sub": "arn:aws:execute-api:${'$'}{AWS::Region}:${'$'}{AWS::AccountId}:${'$'}{${name}API}/*"
                     },
                      "FunctionName":{ 
                        "Fn::Sub": [ 
                            "${'$'}{FuncArn}:${'$'}{AutoPublishAliasName}", 
                            { "FuncArn": { "Fn::GetAtt": ["$name", "Arn"]}} 
                        ]
                      },
                     "Principal": "apigateway.amazonaws.com"
                 }
             },
             """ }
         "${name}IAMRole": {
             "Type": "AWS::IAM::Role",
             "Properties": {
                 "Path": "/",
                 "ManagedPolicyArns": [
                     "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
                 ],
                 "AssumeRolePolicyDocument": {
                     "Version": "2012-10-17",
                     "Statement": [
                         {
                             "Effect": "Allow",
                             "Action": [ "sts:AssumeRole" ],
                             "Principal": {
                                 "Service": [ "lambda.amazonaws.com" ]
                             }
                         }
                     ]
                 },
                 "Policies": [
                     ${getPolicies()}
                 ]
             }
         },
         "${name}API": {
             "Type": "AWS::Serverless::Api",
             "Properties": {
                 "StageName": { "Ref": "StageName" },
                 "DefinitionBody": {
                     "swagger": 2.0,
                     "info": {
                         "title": {
                             "Fn::Sub": "API-$name-${'$'}{StageName}"
                         }
                     },
                     "paths": {
                         "/$path": {
                             "x-amazon-apigateway-any-method": {
                                 "produces": [ "application/json" ],
                                 "x-amazon-apigateway-integration": {
                                     "uri": {
                                         "Fn::Sub": "arn:aws:apigateway:${'$'}{AWS::Region}:lambda:path/2015-03-31/functions/${'$'}{$name.Arn}:${'$'}{AutoPublishAliasName}/invocations"
                                     },
                                     "passthroughBehavior": "when_no_match",
                                     "httpMethod": "POST",
                                     "type": "aws_proxy"
                                 }
                             }
                         }
                     }
                 }
             }
         }
    """.trimIndent()

    private fun getEnvVars(path: String): String {
        return listOf(
                getStageEnv(),
                getRegionEnv(),
                getFunctionEnv(path),
                getProfileEnv()
        ).joinToString(",")
    }

    private fun getPolicies(): String {
        return """{
                             "PolicyName": "CW-Logs",
                             "PolicyDocument": {
                                 "Version": "2012-10-17",
                                 "Statement": [
                                     {
                                         "Effect": "Allow",
                                         "Action": [ "logs:*" ],
                                         "Resource": "*"
                                     }
                                 ]
                             }
                         }"""
    }

    private fun getProfileEnv() = """"spring_profiles_active": "local""""

    private fun getFunctionEnv(path: String) = """"FUNCTION_NAME": "${getFunctionName(path)}""""

    private fun getRegionEnv() = """"REGION": { "Ref": "AWS::Region" }"""

    private fun getStageEnv() = """"STAGE": { "Ref": "StageName" }"""

    private fun getIamRole(name: String) = """${name}IAMRole"""

    private fun getRuntime() = """java11"""

    private fun getHandler() = """com.nilo.cloudfxn.FastStartSpringBootAwsProxyHandler"""

    private fun getFunctionName(path: String) = path
}