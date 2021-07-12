package com.rarible.protocol.nft.api.subscriber

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.ethereum.domain.Blockchain
import com.rarible.protocol.dto.NftItemEventDto
import com.rarible.protocol.dto.NftItemEventTopicProvider
import com.rarible.protocol.dto.NftOwnershipEventDto
import com.rarible.protocol.dto.NftOwnershipEventTopicProvider
import java.util.*

class NftIndexerEventsConsumerFactory(
    private val brokerReplicaSet: String,
    private val blockchain: Blockchain,
    host: String,
    private val environment: String
) {
    private val clientIdPrefix = "$environment.${blockchain.value}.$host.${UUID.randomUUID()}"

    fun createItemEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<NftItemEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.nft-indexer-item-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = NftItemEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = NftItemEventTopicProvider.getTopic(environment, blockchain.value),
            bootstrapServers = brokerReplicaSet
        )
    }

    fun createOwnershipEventsConsumer(consumerGroup: String): RaribleKafkaConsumer<NftOwnershipEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.nft-indexer-ownership-events-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = NftOwnershipEventDto::class.java,
            consumerGroup = consumerGroup,
            defaultTopic = NftOwnershipEventTopicProvider.getTopic(environment, blockchain.value),
            bootstrapServers = brokerReplicaSet
        )
    }
}
