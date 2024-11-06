package com.rarible.protocol.flow.nft.api.client.autoconfigure

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.protocol.flow.nft.api.client.FlowApiServiceUriProvider
import com.rarible.protocol.flow.nft.api.client.FlowNftIndexerApiClientFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@SpringBootConfiguration
@EnableAutoConfiguration
@Import(FlowNftIndexerApiClientAutoConfigurationIt.Configuration::class)
@TestPropertySource(properties = ["flow.indexer.client.k8sNamespace=testnet-protocol"])
class FlowNftIndexerApiClientAutoConfigurationIt {

    @Autowired
    private lateinit var nftIndexerApiServiceUriProvider: FlowApiServiceUriProvider

    @Autowired
    private lateinit var nftIndexerApiClientFactory: FlowNftIndexerApiClientFactory

    @Autowired
    @Qualifier(FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER)
    private lateinit var webClientCustomizer: WebClientCustomizer

    @Test
    fun `test default clients initialized`() {
        assertThat(nftIndexerApiServiceUriProvider).isNotNull
        assertThat(nftIndexerApiClientFactory).isNotNull

        every { webClientCustomizer.customize(any()) } returns Unit

        nftIndexerApiClientFactory.createNftItemApiClient()

        verify { webClientCustomizer.customize(any()) }
    }

    @Test
    fun `test default client uri`() {
        val uri = nftIndexerApiServiceUriProvider.getUri()
        assertThat(uri.toString()).isEqualTo("http://flow-indexer-api.testnet-protocol:8080")
    }

    @TestConfiguration
    internal class Configuration {

        @Bean
        @Qualifier(FLOW_NFT_INDEXER_WEB_CLIENT_CUSTOMIZER)
        fun webClientCustomizer(): WebClientCustomizer {
            return mockk()
        }

        @Bean
        fun applicationEnvironmentInfo(): ApplicationEnvironmentInfo {
            return ApplicationEnvironmentInfo("staging", "test.com")
        }
    }
}
