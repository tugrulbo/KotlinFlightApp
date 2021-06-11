package com.tugrulbo.kotlinflightapp.model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    var count: Int,
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("total")
    var total: Int
)