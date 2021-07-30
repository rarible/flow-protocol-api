package com.rarible.protocol.flow.api

import java.io.InputStream

object FlowOpenapiReader {

    fun getOpenapi(): InputStream =
        FlowOpenapiReader::class.java.getResourceAsStream("/openapi.yaml")!!
}
