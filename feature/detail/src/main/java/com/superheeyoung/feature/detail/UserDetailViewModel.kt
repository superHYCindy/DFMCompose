package com.superheeyoung.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superheeyoung.core.data.repository.GitHubUserRepository
import com.superheeyoung.feature.detail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val gitHubUserRepository: GitHubUserRepository,
) : ViewModel(), ContainerHost<UserDetailState, UserDetailSideEffect> {
    override val container: Container<UserDetailState, UserDetailSideEffect> = container(
        initialState = UserDetailState.Loading(true),
        buildSettings =
        {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(UserDetailSideEffect.ErrorMsg(msg = throwable.message.orEmpty())) }
            }
        }
    )
    var userId = ""
    init {
        userId = savedStateHandle.get<String>("id") ?: ""
        getUserDetail()
    }


    fun getUserDetail() = intent {
        viewModelScope.launch {
            viewModelScope.launch {
                reduce { UserDetailState.Loading(isLoading = true)}
                    if (userId.isBlank()) {
                        reduce { UserDetailState.UserNotFound }
                    } else {
                        getDetailUser(userId.toInt())
                    }
            }
        }
    }

    private fun getDetailUser(id: Int) = intent {
        gitHubUserRepository.getUser(id).fold(
            onSuccess = { dto ->
                val detailModel = dto.toUiModel()
                reduce { UserDetailState.userDetail(avatarUrl = detailModel.avatarUrl) }
            },
            onFailure = {
                reduce { UserDetailState.UserNotFound }
            }
        )
    }
}

sealed interface UserDetailState {
    data class userDetail(
        val avatarUrl: String
    ) : UserDetailState

    data class Loading(
        val isLoading : Boolean
    ) : UserDetailState

    data object UserNotFound : UserDetailState
}

sealed interface UserDetailSideEffect {
    class ErrorMsg(val msg: String) : UserDetailSideEffect
}

