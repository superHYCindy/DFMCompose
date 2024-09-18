package com.superheeyoung.core.local.datasource

import com.superheeyoung.core.data.datasource.local.GitHubUserLocalDataSource
import com.superheeyoung.core.data.model.GitHubUserDto
import com.superheeyoung.core.local.dao.GitHubUserDao
import com.superheeyoung.core.local.mapper.toDto
import com.superheeyoung.core.local.mapper.toLocalEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubUserLocalDataSourceImpl @Inject constructor(
    private val gitHubUserDao: GitHubUserDao
) : GitHubUserLocalDataSource {
    override suspend fun getUser(id: Int): GitHubUserDto? {
        return withContext(Dispatchers.IO) {
            gitHubUserDao.getGitHubUserEntity(id)?.toDto()
        }
    }

    override suspend fun insertUsers(gitHubUserDto: List<GitHubUserDto>) {
        return withContext(Dispatchers.IO) {
            gitHubUserDao.insertAll(gitHubUserDto.map { it.toLocalEntity() })
        }
    }

    override suspend fun insertUser(gitHubUserDto: GitHubUserDto) {
        withContext(Dispatchers.IO) {
            gitHubUserDao.insert(gitHubUserDto.toLocalEntity())
        }
    }
}