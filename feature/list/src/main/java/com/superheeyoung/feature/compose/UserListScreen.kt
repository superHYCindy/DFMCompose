package com.superheeyoung.feature.compose

import com.superheeyoung.feature.GitHubUserListViewModel
import com.superheeyoung.feature.list.model.GitHubUserModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun GitHubUsersScreen(
    onClick: (GitHubUserModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GitHubUserListViewModel = viewModel(),
) {
    val items = viewModel.users.collectAsLazyPagingItems()
    UserListScreen(
        modifier = modifier,
        items = items,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreen(
    items: LazyPagingItems<GitHubUserModel>,
    onClick: (GitHubUserModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues = paddingValues),
            contentPadding = PaddingValues(4.dp),
        ) {
            if (items.loadState.refresh == LoadState.Loading) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        text = "Loading Data.."
                    )
                }
            }
            items(
                count = items.itemCount,
                key = { items[it]?.id ?: it }
            ) { index ->
                val item = items[index]
                if (item != null) {
                    UserListItem(
                        item = item,
                        onPhotoClick = onClick
                    )
                }
            }

            if (items.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
