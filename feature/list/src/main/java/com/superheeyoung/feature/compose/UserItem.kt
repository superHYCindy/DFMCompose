package com.superheeyoung.feature.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.superheeyoung.feature.list.model.GitHubUserModel

@Composable
fun UserListItem(
    item: GitHubUserModel,
    onPhotoClick: (GitHubUserModel) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier.fillMaxSize(),
    )
    {
        Card(
            modifier = modifier.padding(8.dp),
            onClick = { onPhotoClick(item) },
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp),
                model = item.avatarUrl,
                contentDescription = "",
            )
        }

        Text(
            text = "이름 : " + item.name,
            modifier = modifier.align(Alignment.CenterVertically)
        )
    }

}
