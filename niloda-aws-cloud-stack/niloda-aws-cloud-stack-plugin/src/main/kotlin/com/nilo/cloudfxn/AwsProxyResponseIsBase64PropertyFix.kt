package com.nilo.cloudfxn

import com.amazonaws.serverless.proxy.model.Headers
import com.fasterxml.jackson.annotation.JsonProperty

data class AwsProxyResponseIsBase64PropertyFix
@JvmOverloads
constructor(
        var statusCode: Int = 0,
        var multiValueHeaders: Headers? = null,
        var body: String? = null,
        var statusDescription: String? = null,
        var headers: Map<String?, String?>? = null,
        private var isBase64Encoded: Boolean = false
) {

    fun addHeader(key: String, value: String) {
        if (multiValueHeaders == null) {
            multiValueHeaders = Headers()
        }
        multiValueHeaders!!.add(key, value)
    }

    @JsonProperty("isBase64Encoded")
    fun getIsBase64Encoded(): Boolean = isBase64Encoded

    fun setIsBase64Encoded(it: Boolean) {
        isBase64Encoded = it
    }
}