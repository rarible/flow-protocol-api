package com.rarible.protocol.flow.nft.api.client.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.flow.nft.api.client.CompositeWebClientCustomizer
import com.rarible.protocol.flow.nft.api.client.DefaultFlowWebClientCustomizer
import com.rarible.protocol.flow.nft.api.client.FlowApiServiceUriProvider
import com.rarible.protocol.flow.nft.api.client.FlowNftIndexerApiClientFactory
import com.rarible.protocol.flow.nft.api.client.NoopWebClientCustomizer
import com.rarible.protocol.flow.nft.api.client.SwarmFlowNftIndexerApiServiceUriProvider
import com.rarible.protocol.flow.nft.api.client.K8FlowNftIndexerApiServiceUriProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean

const val FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER = "FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER"

class FlowNftIndexerApiClientAutoConfiguration(
    private val applicationEnvironmentInfo: ApplicationEnvironmentInfo,
) {

    @Autowired(required = false)
    @Qualifier(FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER)
    private var webClientCustomizer: WebClientCustomizer = NoopWebClientCustomizer()

    @Bean
    @ConditionalOnMissingBean(FlowApiServiceUriProvider::class)
    fun flowNftIndexerApiServiceUriProvider(@Value("\${rarible.core.client.k8s:false}") k8s: Boolean): FlowApiServiceUriProvider {
        return if (k8s) {
            K8FlowNftIndexerApiServiceUriProvider(applicationEnvironmentInfo.name)
        } else {
            SwarmFlowNftIndexerApiServiceUriProvider(applicationEnvironmentInfo.name)
        }
    }

    @Bean
    @ConditionalOnMissingBean(FlowNftIndexerApiClientFactory::class)
    fun flowNftIndexerApiClientFactory(nftIndexerApiServiceUriProvider: FlowApiServiceUriProvider): FlowNftIndexerApiClientFactory {
        val compositeWebClientCustomizer =
            CompositeWebClientCustomizer(listOf(DefaultFlowWebClientCustomizer(), webClientCustomizer))
        return FlowNftIndexerApiClientFactory(nftIndexerApiServiceUriProvider, compositeWebClientCustomizer)
    }
}
