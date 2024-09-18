package com.superheeyoung.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.superheeyoung.core.data.datasource.local.GitHubUserLocalDataSource
import com.superheeyoung.core.data.datasource.remote.GitHubUserRemoteDataSourece
import com.superheeyoung.core.data.model.GitHubUserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitHubUserRepositoryImpl @Inject constructor(
    private val gitHubUserRemoteDataSourece: GitHubUserRemoteDataSourece,
    private val gitHubUserLocalDataSource: GitHubUserLocalDataSource
) : GitHubUserRepository {
    override fun getGitHubUsersPaging(q : String): Flow<PagingData<GitHubUserDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
            ),
            pagingSourceFactory = {
                object : PagingSource<Int, GitHubUserDto>() {
                    override fun getRefreshKey(state: PagingState<Int, GitHubUserDto>): Int? {
                        return state.anchorPosition?.let { anchorPosition->
                            val anchorPage = state.closestPageToPosition(anchorPosition)
                            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                        }
                    }

                    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubUserDto> {
                        return kotlin.runCatching {
                            val currentPosition = params.key ?: 0
                            val response = gitHubUserRemoteDataSourece.getUsers(q)
                            val data = response
                            gitHubUserLocalDataSource.insertUsers(data)

                            LoadResult.Page(
                                data = data,
                                prevKey = null,
                                nextKey = if(response.isEmpty()) null else currentPosition.plus(1)
                            )
                        }.getOrElse {
                            LoadResult.Error(it)
                        }
                    }

                }
            }
        ).flow
    }

    override suspend fun getUser(id: Int): GitHubUserDto {
        TODO("Not yet implemented")
    }
}