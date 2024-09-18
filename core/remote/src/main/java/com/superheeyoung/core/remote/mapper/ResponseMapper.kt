package com.superheeyoung.core.remote.mapper

import com.superheeyoung.core.data.model.GitHubUserDto
import com.superheeyoung.core.remote.response.GitHubUserResponse

fun GitHubUserResponse.toDto(): GitHubUserDto {
    return GitHubUserDto(
        id,
        avatarUrl,
        name
    )
}