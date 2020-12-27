package com.nilo.cloudfxn

import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.model.Headers
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import mu.KotlinLogging
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler

class FastStartSpringBootAwsProxyHandler :
        SpringBootRequestHandler<AwsProxyRequest, AwsProxyResponseIsBase64PropertyFix>() {
    companion object {

        private val log = KotlinLogging.logger { }

        val handler: SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>

        init {
            log.info("Initializing Spring Boot")
            handler = waitForBootThenGetProxy()
            log.info("Spring Boot initialized")
        }

        private fun waitForBootThenGetProxy() =
                try {
                    waitForBoot()
                    getProxyHandler()
                } catch (e: Exception) {
                    throw Exception("Error initializing Spring Boot application", e)
                }

        private fun waitForBoot() {
            if (isEnvNotLocal()) {
                Thread.sleep(40000)
            }
        }

        private fun getProxyHandler() =
                SpringBootLambdaContainerHandler.getAwsProxyHandler(CloudFunctions::class.java, *getSpringProfiles())

        private fun isEnvNotLocal() = !"true".equals(ignoreCase = true, other = System.getenv("AWS_SAM_LOCAL"))

        private fun getSpringProfiles() = System.getenv("spring_profiles_active").split(",").toTypedArray()

    }

    override fun handleRequest(event: AwsProxyRequest, context: Context?): Any? {

        val response = handleWithProxy(event, context)
        val headers = copyHeadersTo(response)

        return AwsProxyResponseIsBase64PropertyFix(
                response.statusCode,
                headers,
                response.body
        )
    }

    private fun handleWithProxy(event: AwsProxyRequest, context: Context?) =
            handler.proxy(event, context)

    private fun copyHeadersTo(response: AwsProxyResponse) =
            Headers().apply {
                response.headers?.forEach { (key: String, value: String) -> add(key, value) }
            }

}