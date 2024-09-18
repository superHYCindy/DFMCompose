package com.superheeyoung.core.remote.response

import com.google.gson.annotations.SerializedName

data class GitHubResponse<T>(
    @SerializedName("items")
    val items: List<T>
)
