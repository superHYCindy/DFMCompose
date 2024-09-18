package com.superheeyoung.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.superheeyoung.core.ui.AppTheme
import com.superheeyoung.feature.compose.GitHubUsersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubUserListActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                GitHubUsersScreen(
                    onClick = {
                        //TODO
                    }
                )
            }
        }
    }
}