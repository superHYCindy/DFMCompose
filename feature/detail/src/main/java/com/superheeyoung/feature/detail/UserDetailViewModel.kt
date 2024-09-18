package com.superheeyoung.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superheeyoung.core.data.repository.GitHubUserRepository
import com.superheeyoung.feature.detail.model.UserDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
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
        initialState = UserDetailState(),
        buildSettings =
        {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(UserDetailSideEffect.ErrorMsg(msg = throwable.message.orEmpty())) }
            }
        }
    )
    init {
        getUserDetail()
    }
    private val userId: StateFlow<String> = savedStateHandle.getStateFlow("id", "")

    fun getUserDetail() = intent {
        viewModelScope.launch {
            reduce { state.copy(loading = true) }
            userId.map {
                if (it.isBlank()) {
                    reduce { state.copy(error = "ID Not Found") }
                } else {
                    getDetailUser(it.toInt())
                }
            }
        }
    }

    private fun getDetailUser(id: Int) = intent {
        gitHubUserRepository.getUser(id).fold(
            onSuccess = {
                reduce { state.copy(avatarUrl = it.avatarUrl, loading = false) }
            },
            onFailure = {
                reduce { state.copy(error = it.message) }
            }
        )
    }
}

data class UserDetailState(
    val avatarUrl: String = "",
    val loading: Boolean = false,
    val error: String? = null
)

sealed interface UserDetailSideEffect {
    class ErrorMsg(val msg: String) : UserDetailSideEffect
}
