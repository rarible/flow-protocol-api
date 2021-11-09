package com.rarible.protocol.dto

class FlowActivityEventTopicProvider {

    companion object {
        const val VERSION = "v1"

        fun getTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.activity"
        }
    }
}
