package com.superheeyoung.feature

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.superheeyoung.core.ui.AppTheme
import com.superheeyoung.feature.compose.GitHubUsersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubUserListActivity : ComponentActivity() {
    private val viewModel: GitHubUserListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                GitHubUsersScreen(
                    onClick = { userModel ->
                        val uri = "cindy://user/detail".toUri()
                            .buildUpon()
                            .appendQueryParameter("id",userModel.id.toString())
                            .build()
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    },
                    viewModel = viewModel,
                    onQueryChange = viewModel::onQueryChange,
                    searchOnClick = viewModel::searchOnClick
                )
            }
        }
    }
}