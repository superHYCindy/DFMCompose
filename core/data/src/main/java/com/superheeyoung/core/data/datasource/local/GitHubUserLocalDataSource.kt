package com.superheeyoung.core.data.datasource.local

import com.superheeyoung.core.data.model.GitHubUserDto

interface GitHubUserLocalDataSource {
    suspend fun getUser(id : Int) : GitHubUserDto?
    suspend fun insertUsers(gitHubUserDto: List<GitHubUserDto>)
    suspend fun insertUser(gitHubUserDto: GitHubUserDto)
}