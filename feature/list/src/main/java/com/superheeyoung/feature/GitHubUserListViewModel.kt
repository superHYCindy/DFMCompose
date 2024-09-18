package com.superheeyoung.feature

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import androidx.paging.map
import com.superheeyoung.core.data.repository.GitHubUserRepository
import com.superheeyoung.feature.list.model.GitHubUserModel
import com.superheeyoung.feature.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GitHubUserListViewModel @Inject constructor(
    private val gitHubUserRepository: GitHubUserRepository
) : ViewModel() {
    lateinit var model : GitHubUserModel
    fun getUsers(q : String) {
        gitHubUserRepository.getGitHubUsersPaging(q)
            .map { pagingData ->
                pagingData.map {
                    model = it.toUiModel()
                    Log.d("debug2323",model.toString())
                }
            }
            .cachedIn(viewModelScope)
    }
}