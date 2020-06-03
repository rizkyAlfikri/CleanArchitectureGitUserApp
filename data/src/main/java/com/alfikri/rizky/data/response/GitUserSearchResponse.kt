package com.alfikri.rizky.data.response


import com.google.gson.annotations.SerializedName

data class GitUserSearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val gitUserResponses: List<GitUserResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)