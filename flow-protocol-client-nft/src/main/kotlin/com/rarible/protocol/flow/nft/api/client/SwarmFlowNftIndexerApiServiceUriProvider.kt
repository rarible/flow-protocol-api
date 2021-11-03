package com.rarible.protocol.flow.nft.api.client

import java.net.URI

class SwarmFlowNftIndexerApiServiceUriProvider(
    private val environment: String
) : FlowNftIndexerApiServiceUriProvider {

    override fun getUri(blockchain: String): URI {
        return URI.create("https://$environment-flow-indexer-backend-api")
    }

}
