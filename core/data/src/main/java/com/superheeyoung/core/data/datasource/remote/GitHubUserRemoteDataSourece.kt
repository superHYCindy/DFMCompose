package com.superheeyoung.core.data.datasource.remote

import com.superheeyoung.core.data.model.GitHubUserDto

interface GitHubUserRemoteDataSourece {
    suspend fun getUsers(q : String, page : Int, perPage : Int) : List<GitHubUserDto>
}