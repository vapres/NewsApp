package com.route.test.model.source

import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @field:SerializedName("sources")
    val sources: List<SourceItem>? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("message")
    val message: String? = null
)