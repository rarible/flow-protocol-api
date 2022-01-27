package com.rarible.protocol.flow.nft.api.client

import com.rarible.protocol.flow.nft.api.ApiClient
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer

open class FlowNftIndexerApiClientFactory(
    private val uriProvider: FlowApiServiceUriProvider,
    private val webClientCustomizer: WebClientCustomizer
) {

    fun createNftItemApiClient(): FlowNftItemControllerApi {
        return FlowNftItemControllerApi(createApiClient())
    }

    fun createNftOwnershipApiClient(): FlowNftOwnershipControllerApi {
        return FlowNftOwnershipControllerApi(createApiClient())
    }

    fun createNftOrderApiClient(): FlowOrderControllerApi {
        return FlowOrderControllerApi(createApiClient())
    }

    fun createBidApiClient(): FlowBidOrderControllerApi {
        return FlowBidOrderControllerApi(createApiClient())
    }

    fun createNftCollectionApiClient(): FlowNftCollectionControllerApi {
        return FlowNftCollectionControllerApi(createApiClient())
    }

    fun createNftOrderActivityApiClient(): FlowNftOrderActivityControllerApi {
        return FlowNftOrderActivityControllerApi(createApiClient())
    }

    fun createCryptoApiClient(): FlowNftCryptoControllerApi {
        return FlowNftCryptoControllerApi(createApiClient())
    }

    fun createOrderAggregationControllerApi(): OrderAggregationControllerApi {
        return OrderAggregationControllerApi(createApiClient())
    }

    private fun createApiClient(): ApiClient {
        return ApiClient(webClientCustomizer)
            .setBasePath(uriProvider.getUri().toASCIIString())
    }
}

