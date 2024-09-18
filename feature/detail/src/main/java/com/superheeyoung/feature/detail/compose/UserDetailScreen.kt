package com.superheeyoung.feature.detail.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.superheeyoung.feature.detail.UserDetailViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: UserDetailViewModel,
) {
    viewModel.getUserDetail()
    val uiState = viewModel.collectAsState().value
    Log.d("debug2424",uiState.avatarUrl)
    /*PhotoDetailScreen(
        modifier = modifier,
        uiState = uiState,
    )*/
}