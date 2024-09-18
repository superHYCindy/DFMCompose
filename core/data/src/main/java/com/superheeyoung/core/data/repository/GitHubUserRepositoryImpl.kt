package com.superheeyoung.core.data.repository

import android.util.Log
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
    override fun getGitHubUsersPaging(q: String): Flow<PagingData<GitHubUserDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                object : PagingSource<Int, GitHubUserDto>() {
                    override fun getRefreshKey(state: PagingState<Int, GitHubUserDto>): Int? {
                        return state.anchorPosition?.let { anchorPosition ->
                            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                        }
                    }

                    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubUserDto> {
                        return kotlin.runCatching {
                            val page = params.key ?: STARTING_PAGE_INDEX
                            val response =
                                gitHubUserRemoteDataSourece.getUsers(q, page, params.loadSize)
                            gitHubUserLocalDataSource.insertUsers(response)

                            LoadResult.Page(
                                data = response,
                                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                                nextKey = if (response.isEmpty()) null else page.plus(1)
                            )
                        }.getOrElse {
                            LoadResult.Error(it)
                        }
                    }

                }
            }
        ).flow
    }

    override suspend fun getUser(id: Int): Result<GitHubUserDto> {
        return kotlin.runCatching {
            gitHubUserLocalDataSource.getUser(id) ?: throw NoSuchElementException("User Not Found")
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}

