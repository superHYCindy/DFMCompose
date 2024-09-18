package com.superheeyoung.core.local.di.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.superheeyoung.core.local.dao.GitHubUserDao
import com.superheeyoung.core.local.model.GitHubUserEntity

@Database(
    entities = [
        GitHubUserEntity::class
    ],
    version = 1
)

abstract class UserDataBase : RoomDatabase() {
    abstract fun gitHubUserDao(): GitHubUserDao
}