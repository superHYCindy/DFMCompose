package com.superheeyoung.feature.detail.model

import com.superheeyoung.core.data.model.GitHubUserDto

data class UserDetailModel(
    val id : Int,
    val avatarUrl: String,
    val name: String,
)

fun GitHubUserDto.toUiModel() : UserDetailModel {
    return UserDetailModel(
        id,
        avatarUrl,
        name
    )
}