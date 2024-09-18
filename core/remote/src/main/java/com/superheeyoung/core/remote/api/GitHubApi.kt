package com.superheeyoung.core.remote.api

import com.superheeyoung.core.remote.response.GitHubResponse
import com.superheeyoung.core.remote.response.GitHubUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("/search/users")
    fun getGitHubUser(
        @Query("q") name: String
    ): GitHubResponse<GitHubUserResponse>
}