package com.example.amusic.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.amusic.R
import com.example.amusic.presentation.model.TrackUi
import com.example.amusic.ui.theme.AMusicTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    trackUi: TrackUi = TrackUi(),
    onItemClick: (trackId: Long) -> Unit = {},
    onClickDownload: (trackId: Long) -> Unit = {}
) {

    Row(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onItemClick(trackUi.id) }
            )
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = trackUi.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(48.dp)
                .background(AMusicTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = trackUi.title, style = AMusicTheme.typography.title2, color = Color.White)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = trackUi.author, style = AMusicTheme.typography.body2, color = Color.White)
        }
        Icon(
            painter = painterResource(R.drawable.ic_downloads),
            contentDescription = null,
            tint = if (trackUi.isDownloaded) AMusicTheme.colorScheme.secondary else AMusicTheme.colorScheme.secondaryVariant,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClickDownload(trackUi.id) }
            )
        )
    }
}

@Preview
@Composable
private fun TrackItemPreview() {
    AMusicTheme {
        var trackUi by remember { mutableStateOf(TrackUi()) }
        TrackItem(modifier = Modifier.fillMaxWidth(), trackUi, onClickDownload = {
            trackUi = trackUi.copy(isDownloaded = !trackUi.isDownloaded)
        })
    }
}