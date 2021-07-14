package com.rarible.protocol.flow.nft.api.client.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.client.CompositeWebClientCustomizer
import com.rarible.protocol.client.DefaultProtocolWebClientCustomizer
import com.rarible.protocol.client.NoopWebClientCustomizer
import com.rarible.protocol.flow.nft.api.client.FlowNftIndexerApiClientFactory
import com.rarible.protocol.flow.nft.api.client.FlowNftIndexerApiServiceUriProvider
import com.rarible.protocol.flow.nft.api.client.SwarmFlowNftIndexerApiServiceUriProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean

const val FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER = "FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER"

class FlowNftIndexerApiClientAutoConfiguration(
    private val applicationEnvironmentInfo: ApplicationEnvironmentInfo
) {

    @Autowired(required = false)
    @Qualifier(FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER)
    private var webClientCustomizer: WebClientCustomizer = NoopWebClientCustomizer()

    @Bean
    @ConditionalOnMissingBean(FlowNftIndexerApiServiceUriProvider::class)
    fun flowNftIndexerApiServiceUriProvider(): FlowNftIndexerApiServiceUriProvider {
        return SwarmFlowNftIndexerApiServiceUriProvider(applicationEnvironmentInfo.name)
    }

    @Bean
    @ConditionalOnMissingBean(FlowNftIndexerApiClientFactory::class)
    fun flowNftIndexerApiClientFactory(nftIndexerApiServiceUriProvider: FlowNftIndexerApiServiceUriProvider): FlowNftIndexerApiClientFactory {
        val compositeWebClientCustomizer =
            CompositeWebClientCustomizer(listOf(DefaultProtocolWebClientCustomizer(), webClientCustomizer))
        return FlowNftIndexerApiClientFactory(nftIndexerApiServiceUriProvider, compositeWebClientCustomizer)
    }
}