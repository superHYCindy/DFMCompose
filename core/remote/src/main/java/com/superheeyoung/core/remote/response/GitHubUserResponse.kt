package com.superheeyoung.core.remote.response

import com.google.gson.annotations.SerializedName

data class GitHubUserResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val name: String,
)
