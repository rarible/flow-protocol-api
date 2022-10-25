package com.rarible.protocol.flow.nft.api.client

import java.net.URI

class SwarmFlowNftIndexerApiServiceUriProvider(
    private val environment: String
) : FlowApiServiceUriProvider {

    override fun getUri(): URI {
        return URI.create("http://$environment-flow-indexer-backend-api:8080")
    }

}
