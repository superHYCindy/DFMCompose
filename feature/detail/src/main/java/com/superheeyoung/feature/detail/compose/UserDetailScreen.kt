package com.superheeyoung.feature.detail.compose

import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.superheeyoung.feature.detail.UserDetailState
import com.superheeyoung.feature.detail.UserDetailViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: UserDetailViewModel,
) {
    val state = viewModel.collectAsState().value
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackPressedDispatcher?.onBackPressed() }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기"
                        )
                    }
                },
                title = {
                    Text(text = "Detail Image")
                })
        }
    ) { paddingValue ->
        when (state) {
            is UserDetailState.Loading -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValue)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    text = "Data Loading.."
                )
            }

            UserDetailState.UserNotFound -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValue)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    text = "Sorry. User Not Found"
                )
            }

            is UserDetailState.userDetail -> {
                UserDetailImage(
                    modifier = Modifier.padding(paddingValue),
                    avataUrl = state.avatarUrl
                )
            }
        }
    }
}

@Composable
private fun UserDetailImage(
    avataUrl: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.padding(40.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                model = avataUrl,
                contentDescription = "github user avataurl",
            )
        }
    }

}