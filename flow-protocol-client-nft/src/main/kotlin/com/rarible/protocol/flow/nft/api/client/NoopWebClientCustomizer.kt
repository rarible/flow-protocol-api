package com.rarible.protocol.flow.nft.api.client

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.web.reactive.function.client.WebClient

class NoopWebClientCustomizer : WebClientCustomizer {

    override fun customize(webClientBuilder: WebClient.Builder?) {

    }
}