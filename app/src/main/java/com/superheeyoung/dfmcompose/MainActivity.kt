package com.superheeyoung.dfmcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.superheeyoung.feature.GitHubUserListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(
            Intent(this, GitHubUserListActivity::class.java))
        finish()
    }
}