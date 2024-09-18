package com.superheeyoung.feature.mapper

import com.superheeyoung.core.data.model.GitHubUserDto
import com.superheeyoung.feature.list.model.GitHubUserModel

fun GitHubUserDto.toUiModel() : GitHubUserModel {
    return GitHubUserModel(
        id,
        avatarUrl,
        name
    )
}