package com.rarible.protocol.dto

class FlowNftCollectionEventTopicProvider {

    companion object {
        const val VERSION = "v1"

        fun getTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.collection"
        }
    }
}