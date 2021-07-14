package com.rarible.protocol.flow.nft.api.client

import java.net.URI

class FixedFlowNftIndexerApiServiceUriProvider(private val fixedURI: URI) : FlowNftIndexerApiServiceUriProvider {

    override fun getUri(blockchain: String): URI {
        return fixedURI
    }

}
