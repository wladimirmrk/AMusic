package com.example.amusic.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.amusic.presentation.model.TrackUi
import com.example.amusic.ui.theme.AMusicTheme

val trackUis = Array(50) {
    TrackUi(id = it)
}.toList()

@Composable
fun TrackListScreen(
    title: String,
    query: String,
    onQueryChanged: (query: String) -> Unit,
    trackUiList: List<TrackUi>,
    onClickDownload: (trackId: Int) -> Unit,
    onClickTrack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Text(
                title,
                style = AMusicTheme.typography.headline,
                color = AMusicTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)
            )
        }
        item {
            Search(
                query = query,
                onQueryChanged = onQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp)
            )
        }
        itemsIndexed(trackUiList, key = { _, item: TrackUi -> item.id }) { index, item ->
            val top = if (index == 0) 20.dp else 8.dp
            val bottom = if (index == trackUis.lastIndex) 20.dp else 8.dp
            TrackItem(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = top, end = 20.dp, bottom = bottom),
                trackUi = item,
                onClickDownload = onClickDownload
            )
        }
    }
}