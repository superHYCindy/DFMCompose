package com.superheeyoung.core.data.datasource.remote

import com.superheeyoung.core.data.model.GitHubUserDto

//TODO
interface GitHubUserRemoteDataSourece {
    suspend fun getUsers(q : String) : List<GitHubUserDto>
    suspend fun getUser() : GitHubUserDto
}