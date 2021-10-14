package com.rarible.protocol.flow.nft.api.client

import com.rarible.protocol.client.AbstractApiClientFactory
import com.rarible.protocol.client.ApiServiceUriProvider
import com.rarible.protocol.flow.nft.api.ApiClient
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer

open class FlowNftIndexerApiClientFactory(
    uriProvider: ApiServiceUriProvider,
    webClientCustomizer: WebClientCustomizer
) : AbstractApiClientFactory(uriProvider, webClientCustomizer) {


    fun createNftItemApiClient(blockchain: String): FlowNftItemControllerApi {
        return FlowNftItemControllerApi(createApiClient(blockchain))
    }

    fun createNftOwnershipApiClient(blockchain: String): FlowNftOwnershipControllerApi {
        return FlowNftOwnershipControllerApi(createApiClient(blockchain))
    }

    fun createNftOrderApiClient(blockchain: String): FlowOrderControllerApi {
        return FlowOrderControllerApi(createApiClient(blockchain))
    }

    fun createNftCollectionApiClient(blockchain: String): FlowNftCollectionControllerApi {
        return FlowNftCollectionControllerApi(createApiClient(blockchain))
    }

    fun createNftOrderActivityApiClient(blockchain: String): FlowNftOrderActivityControllerApi {
        return FlowNftOrderActivityControllerApi(createApiClient(blockchain))
    }

    fun createCryptoApiClient(blockchain: String): FlowNftCryptoControllerApi {
        return FlowNftCryptoControllerApi(createApiClient(blockchain))
    }

    private fun createApiClient(blockchain: String): ApiClient {
        return ApiClient(webClientCustomizer)
            .setBasePath(getBasePath(blockchain))
    }
}

