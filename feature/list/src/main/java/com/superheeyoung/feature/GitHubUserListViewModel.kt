package com.superheeyoung.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.superheeyoung.core.data.repository.GitHubUserRepository
import com.superheeyoung.feature.list.model.GitHubUserModel
import com.superheeyoung.feature.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class GitHubUserListViewModel @Inject constructor(
    private val gitHubUserRepository: GitHubUserRepository
) : ViewModel(), ContainerHost<GitHubUsersState, GitHubUserSideEffect> {


    override val container: Container<GitHubUsersState, GitHubUserSideEffect> = container(
        initialState = GitHubUsersState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(GitHubUserSideEffect.ErrorMsg(msg = throwable.message.orEmpty())) }
            }
        }
    )

    fun onQueryChange(query: String) = blockingIntent {
        reduce {
            state.copy(query = query)
        }
    }

    fun searchOnClick() = intent {
        val query = state.query
        val gitHubUsersFlow = gitHubUserRepository.getGitHubUsersPaging(query)
            .map { pagingData ->
                pagingData
                    .map {
                        it.toUiModel()
                    }
            }
        reduce {
            state.copy(
                gitHubUserModelFlow = gitHubUsersFlow
            )
        }
    }

    fun onClickUserItem() = intent {
        postSideEffect(GitHubUserSideEffect.NavigateToDetailActivity)
    }
}

data class GitHubUsersState(
    val query: String = "",
    val gitHubUserModelFlow: Flow<PagingData<GitHubUserModel>> = emptyFlow()
)

sealed interface GitHubUserSideEffect {
    class ErrorMsg(val msg: String) : GitHubUserSideEffect //에러 체크용
    data object NavigateToDetailActivity : GitHubUserSideEffect
}
