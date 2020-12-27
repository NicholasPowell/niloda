package com.nilo

import com.nilo.dependencies.Aws
import org.junit.jupiter.api.Test

class ExtensionTests {

    @Test
    fun testVersionExtension() {
        Aws.apiGatewaySdk
    }


}