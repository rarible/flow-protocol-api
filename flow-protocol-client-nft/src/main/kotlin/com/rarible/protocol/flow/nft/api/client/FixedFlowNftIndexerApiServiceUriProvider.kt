package com.rarible.protocol.flow.nft.api.client

import java.net.URI

class FixedFlowNftIndexerApiServiceUriProvider(
    private val fixedURI: URI
) : FlowApiServiceUriProvider {

    override fun getUri(): URI {
        return fixedURI
    }

}
