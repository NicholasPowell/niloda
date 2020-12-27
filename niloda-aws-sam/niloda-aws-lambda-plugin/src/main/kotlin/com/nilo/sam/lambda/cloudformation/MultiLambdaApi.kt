package com.nilo.sam.lambda.cloudformation

class MultiLambdaApi(
        names: List<String>,
        firstTime: Boolean,
        codeUri: String,
        lambdaLayers: LambdaLayersResource,
        private val headers: LambdaHeaders = LambdaHeaders(),
        private val parameters: LambdaParameters = LambdaParameters(),
        private val resources: LambdaWithApiResource = LambdaWithApiResource(names, firstTime, codeUri, lambdaLayers),
        private val kmsResources: KmsResource = KmsResource(),
        private val outputs: LambdaWithApiOutput = LambdaWithApiOutput(names)
) {
    fun format(description: String = "aws-sam-plugin:MultiLambdaApi"): String = """
         {
             ${headers.getHeaders(description)}
             "Parameters": {
                 ${parameters.getParameters()}
             },
             "Resources": {
                ${ listOf(
                            resources.getResources()
//                            kmsResources.getResources(
//                                    listOf(
//                                            "subnet-0810113a4938ec42f",
//                                            "subnet-0b95fa8a14c04f2df"
//                                    ), 
//                                    listOf("sg-0f53e2b419af8fe83")
//                            )
                          ).joinToString(",") }        
             },
             "Outputs": {
                ${outputs.getOutputs()}    
             }
         }
    """
            .trimIndent()
}