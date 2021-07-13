package com.rarible.protocol.dto

class FlowNftOwnershipEventTopicProvider {

    companion object {
        const val VERSION = "v1"

        fun getTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.ownership"
        }
    }
}
