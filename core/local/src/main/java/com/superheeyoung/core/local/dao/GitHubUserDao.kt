package com.superheeyoung.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.superheeyoung.core.local.model.GitHubUserEntity

@Dao
interface GitHubUserDao {
    @Query("SELECT * FROM GitHubUserEntity Where id = :id")
    suspend fun getGitHubUserEntity(id : Int) : GitHubUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gitHubUserEntities : List<GitHubUserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gitHubUserEntity: GitHubUserEntity)
}