package com.nilo.sam.lambda.cloudformation

class LambdaWithApiOutput(val names: List<String>) {

    fun getOutputs() = names.joinToString(",") { getOutput(it) }
    fun getOutput(name: String, path: String = name.toLowerCase()) = """
        "$name": {
             "Description": "Test for refactored FastStart Handler",
             "Value": { "Fn::GetAtt": [ "$name", "Arn" ] }
         },
         "${name}APIUrl": {
             "Value": {
                 "Fn::Sub": "https://${'$'}{${name}API}.execute-api.${'$'}{AWS::Region}.amazonaws.com/${'$'}{StageName}/$path"
             }
         }
    """.trimIndent()
}