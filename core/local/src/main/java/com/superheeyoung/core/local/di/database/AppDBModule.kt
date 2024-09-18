package com.superheeyoung.core.local.di.database

import android.content.Context
import androidx.room.Room
import com.superheeyoung.core.local.dao.GitHubUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDBModule {
    @Provides
    @Singleton
    fun providesUserDataBase(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(
            context,
            UserDataBase::class.java,
            "UserDataBase"
        )
            .build()
    }


    @Provides
    @Singleton
    fun provideGitHubUserDao(userDataBase: UserDataBase): GitHubUserDao {
        return userDataBase.gitHubUserDao()
    }
}