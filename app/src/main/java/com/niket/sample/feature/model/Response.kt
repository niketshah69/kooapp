package com.niket.sample.feature.model

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("data")
    val data: List<PostResponseItem>? = null,

    @field:SerializedName("meta")
    val meta: Meta? = null
)

data class PostResponseItem(

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null
)

data class Links(

    @field:SerializedName("next")
    val next: String? = null,

    @field:SerializedName("current")
    val current: String? = null,

    @field:SerializedName("previous")
    val previous: Any? = null
)

data class Pagination(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("pages")
    val pages: Int? = null,

    @field:SerializedName("limit")
    val limit: Int? = null,

    @field:SerializedName("links")
    val links: Links? = null,

    @field:SerializedName("page")
    val page: Int? = null
)

data class Meta(

    @field:SerializedName("pagination")
    val pagination: Pagination? = null
)
