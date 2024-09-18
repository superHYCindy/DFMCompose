package com.superheeyoung.feature.compose


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import com.superheeyoung.feature.GitHubUserListViewModel
import com.superheeyoung.feature.list.model.GitHubUserModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GitHubUsersScreen(
    viewModel: GitHubUserListViewModel,
    onQueryChange: (String) -> Unit,
    searchOnClick: () -> Unit,
    onClick: (GitHubUserModel) -> Unit,
    modifier: Modifier = Modifier,

) {
    val state = viewModel.collectAsState().value
    UserListScreen(
        query = state.query,
        modifier = modifier,
        items = state.gitHubUserModelFlow.collectAsLazyPagingItems(),
        onClick = onClick,
        onQueryChange = onQueryChange,
        searchOnClick = searchOnClick
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreen(
    query: String,
    items: LazyPagingItems<GitHubUserModel>,
    onClick: (GitHubUserModel) -> Unit,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    searchOnClick: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputTextField(
                    value = query,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(60.dp)
                        .weight(2f)
                        .padding(end = 8.dp),
                    onValueChange = onQueryChange
                )
                Button(
                    modifier = modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = searchOnClick
                ) {
                    Text(
                        text = "검색",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
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
}
