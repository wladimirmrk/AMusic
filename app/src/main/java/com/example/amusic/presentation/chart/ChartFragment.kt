package com.example.amusic.presentation.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.amusic.presentation.components.Search
import com.example.amusic.presentation.components.TrackItem
import com.example.amusic.presentation.model.Track
import com.example.amusic.ui.theme.AMusicTheme


class ChartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AMusicTheme {
                ChartScreen()
            }
        }
    }
}

private val tracks = Array(50) {
    Track(id = it)
}

@Composable
fun ChartScreen(

) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                "Chart",
                style = AMusicTheme.typography.headline,
                color = AMusicTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)
            )
        }
        item {
            Search(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp)
            )
        }
        itemsIndexed(tracks, key = { _, item: Track -> item.id }) { index, item ->
            val top = if (index == 0) 20.dp else 8.dp
            val bottom = if (index == tracks.lastIndex) 20.dp else 8.dp
            TrackItem(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = top, end = 20.dp, bottom = bottom),
                track = item
            )
        }

    }
}

@Preview
@Composable
private fun ChartScreenPreview() {
    AMusicTheme {
        ChartScreen()
    }
}