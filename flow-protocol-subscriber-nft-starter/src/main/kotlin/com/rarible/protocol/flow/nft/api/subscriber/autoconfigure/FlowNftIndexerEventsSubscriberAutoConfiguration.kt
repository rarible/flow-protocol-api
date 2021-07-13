package com.rarible.protocol.flow.nft.api.subscriber.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.flow.nft.api.subscriber.FlowNftIndexerEventsConsumerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@EnableConfigurationProperties(FlowNftIndexerEventsSubscriberProperties::class)
class FlowNftIndexerEventsSubscriberAutoConfiguration(
    private val applicationEnvironmentInfo: ApplicationEnvironmentInfo,
    private val propertiesFlow: FlowNftIndexerEventsSubscriberProperties
) {
    @Bean
    @ConditionalOnMissingBean(FlowNftIndexerEventsConsumerFactory::class)
    fun flowNftIndexerEventsConsumerFactory(): FlowNftIndexerEventsConsumerFactory {
        return FlowNftIndexerEventsConsumerFactory(
            brokerReplicaSet = propertiesFlow.brokerReplicaSet,
            host = applicationEnvironmentInfo.host,
            environment = applicationEnvironmentInfo.name
        )
    }
}
