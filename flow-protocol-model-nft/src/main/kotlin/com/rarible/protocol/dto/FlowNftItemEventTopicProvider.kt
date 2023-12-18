package com.rarible.protocol.dto

class FlowNftItemEventTopicProvider {

    companion object {
        const val VERSION = "v1"

        fun getTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.item"
        }

        fun getItemMetaTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.item.meta"
        }
    }
}
