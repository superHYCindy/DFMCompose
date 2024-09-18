package com.superheeyoung.core.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitHubUserEntity(
    @PrimaryKey
    val id : Int,
    val avatarUrl: String,
    val name: String,
)
