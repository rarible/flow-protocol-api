package com.rarible.protocol.dto

class FlowActivityEventTopicProvider {

    companion object {
        const val VERSION = "v1"

        @Deprecated("Use getActivityTopic")
        fun getTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.nft.activity"
        }

        fun getActivityTopic(environment: String): String {
            return "protocol.$environment.flow.indexer.activity"
        }
    }
}
