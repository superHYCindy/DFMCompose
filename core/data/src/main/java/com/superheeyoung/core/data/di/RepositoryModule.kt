package com.superheeyoung.core.data.di

import com.superheeyoung.core.data.repository.GitHubUserRepository
import com.superheeyoung.core.data.repository.GitHubUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindGitHubUserRepository(
        impl : GitHubUserRepositoryImpl
    ) : GitHubUserRepository
}