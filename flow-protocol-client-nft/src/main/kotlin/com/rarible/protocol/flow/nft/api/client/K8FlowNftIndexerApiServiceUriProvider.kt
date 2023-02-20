package com.rarible.protocol.flow.nft.api.client

import java.net.URI

class K8FlowNftIndexerApiServiceUriProvider(
    private val environment: String
): FlowApiServiceUriProvider {
    override fun getUri(): URI = URI.create("http://flow-indexer-api.${environment}-protocol:8080")
}
