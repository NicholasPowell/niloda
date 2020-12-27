package com.nilo.sam.lambda.cloudformation

class LambdaHeaders {
    fun getHeaders(description: String): String {
        return """
            "AWSTemplateFormatVersion": "2010-09-09",
            "Transform": "AWS::Serverless-2016-10-31",
            "Description": "$description",""".trimIndent()
    }
}
