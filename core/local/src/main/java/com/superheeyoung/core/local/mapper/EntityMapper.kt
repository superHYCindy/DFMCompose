package com.superheeyoung.core.local.mapper

import com.superheeyoung.core.data.model.GitHubUserDto
import com.superheeyoung.core.local.model.GitHubUserEntity

fun GitHubUserDto.toLocalEntity() = GitHubUserEntity(
    id,
    avatarUrl,
    name
)

fun GitHubUserEntity.toDto() = GitHubUserDto(
    id,
    avatarUrl,
    name
)