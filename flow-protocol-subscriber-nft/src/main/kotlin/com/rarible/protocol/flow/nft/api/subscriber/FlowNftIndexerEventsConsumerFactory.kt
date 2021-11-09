package com.rarible.protocol.flow.nft.api.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.protocol.dto.*
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

    fun createOwnershipEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<FlowOwnershipEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.flow-nft-indexer-ownership-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = FlowOwnershipEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = FlowNftOwnershipEventTopicProvider.getTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
    }

    fun createOrderEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<FlowOrderEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.flow-nft-indexer-order-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = FlowOrderEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = FlowOrderEventTopicProvider.getTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
    }

    fun createAcitivityEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<FlowActivityDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.flow-nft-indexer-activity-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = FlowActivityDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = FlowActivityEventTopicProvider.getTopic(environment),
            bootstrapServers = brokerReplicaSet
        )
    }

}
