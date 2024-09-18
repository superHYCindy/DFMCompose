package com.superheeyoung.core.remote.di

import com.superheeyoung.core.data.datasource.remote.GitHubUserRemoteDataSourece
import com.superheeyoung.core.remote.datasource.GitHubUserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    @Singleton
    fun bindGitHubUserRemoteDataSource(impl: GitHubUserRemoteDataSourceImpl): GitHubUserRemoteDataSourece
}