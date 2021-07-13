package com.rarible.protocol.dto

class FlowOrderEventTopicProvider {

    companion object {
        const val VERSION = "v1"

        fun getTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.order"
        }
    }
}
