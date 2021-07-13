package com.rarible.protocol.flow.nft.api.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.protocol.dto.FlowNftItemEventDto
import com.rarible.protocol.dto.FlowNftItemEventTopicProvider
import com.rarible.protocol.dto.FlowOrderDto
import com.rarible.protocol.dto.FlowOrderEventTopicProvider
import java.util.*

class FlowNftIndexerEventsConsumerFactory(
    private val brokerReplicaSet: String,
    host: String,
    private val environment: String
) {

    private val clientIdPrefix = "$environment.flow.$host.${UUID.randomUUID()}"

    fun createItemEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<FlowNftItemEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.flow-nft-indexer-item-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = FlowNftItemEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = FlowNftItemEventTopicProvider.getTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
    }

    fun createORderEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<FlowOrderDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.flow-nft-indexer-order-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = FlowOrderDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = FlowOrderEventTopicProvider.getTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
    }

}
