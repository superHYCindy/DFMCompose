package com.superheeyoung.feature.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.superheeyoung.core.ui.AppTheme
import com.superheeyoung.feature.detail.compose.UserDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : ComponentActivity() {
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLink()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    UserDetailScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    private fun handleDeepLink() {
        val extras = intent.extras?.deepCopy() ?: Bundle()
        val data = intent.data
        data?.queryParameterNames?.forEach {
            extras.putString(it, data.getQueryParameter(it))
        }
        intent.putExtras(extras)
    }
}