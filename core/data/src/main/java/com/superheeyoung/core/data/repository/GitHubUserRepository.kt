package com.superheeyoung.core.data.repository

import androidx.paging.PagingData
import com.superheeyoung.core.data.model.GitHubUserDto
import kotlinx.coroutines.flow.Flow

interface GitHubUserRepository {
    fun getGitHubUsersPaging(q : String) : Flow<PagingData<GitHubUserDto>>
    suspend fun getUser(id : Int) : Result<GitHubUserDto>
}