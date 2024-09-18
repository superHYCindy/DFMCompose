package com.superheeyoung.core.remote.datasource

import com.superheeyoung.core.data.model.GitHubUserDto
import com.superheeyoung.core.data.datasource.remote.GitHubUserRemoteDataSourece
import com.superheeyoung.core.remote.api.GitHubApi
import com.superheeyoung.core.remote.mapper.toDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubUserRemoteDataSourceImpl @Inject constructor(
    private val gitHubApi: GitHubApi
) : GitHubUserRemoteDataSourece {
    override suspend fun getUsers(q: String): List<GitHubUserDto> {
        return withContext(Dispatchers.IO) {
            gitHubApi.getGitHubUser(q).items.map { it.toDto() }
        }
    }

    override suspend fun getUser(): GitHubUserDto {
        TODO("Not yet implemented")
    }
}