package com.nilo.sam.lambda.cloudformation

class LambdaParameters {
    fun getParameters(): String {
        return """"StageName": {
                 "Type": "String",
                 "Default": "prod",
                 "Description": "The Lambda Function and API Gateway Stage"
             },
             "AutoPublishAliasName": {
                 "Type": "String",
                 "Default": "fast",
                 "Description": "The alias used for Auto Publishing"
             }"""
    }

}
