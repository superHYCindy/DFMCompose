package com.superheeyoung.core.local.di.datasource

import com.superheeyoung.core.data.datasource.local.GitHubUserLocalDataSource
import com.superheeyoung.core.local.datasource.GitHubUserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
    @Binds
    @Singleton
    fun bindGitHubUserLocalDataSource(impl : GitHubUserLocalDataSourceImpl) : GitHubUserLocalDataSource
}