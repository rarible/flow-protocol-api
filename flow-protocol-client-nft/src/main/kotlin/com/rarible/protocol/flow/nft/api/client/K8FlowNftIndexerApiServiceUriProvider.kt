package com.rarible.protocol.flow.nft.api.client

import java.net.URI

class K8FlowNftIndexerApiServiceUriProvider(
    // Deprecated. TODO remove when all downstream are updated
    environment: String,
    private val k8sNamespace: String,
): FlowApiServiceUriProvider {
    override fun getUri(): URI = URI.create("http://flow-indexer-api.$k8sNamespace:8080")
}
