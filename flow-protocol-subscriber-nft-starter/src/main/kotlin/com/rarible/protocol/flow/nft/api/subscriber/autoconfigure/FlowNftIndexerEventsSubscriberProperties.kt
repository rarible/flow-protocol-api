package com.rarible.protocol.flow.nft.api.subscriber.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

internal const val PROTOCOL_FLOW_NFT_SUBSCRIBER = "protocol.flow-nft.subscriber"

@ConfigurationProperties(PROTOCOL_FLOW_NFT_SUBSCRIBER)
@ConstructorBinding
data class FlowNftIndexerEventsSubscriberProperties(
    val brokerReplicaSet: String

)
