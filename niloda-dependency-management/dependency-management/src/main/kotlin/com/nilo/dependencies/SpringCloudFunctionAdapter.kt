package com.nilo.dependencies

object SpringCloudFunctionAdapter {

    val web = WithoutVersion.web
    val aws = WithoutVersion.aws

    object WithoutVersion {
        const val web = "org.springframework.cloud:spring-cloud-starter-function-web"
        const val aws = "org.springframework.cloud:spring-cloud-function-adapter-aws"
    }
}