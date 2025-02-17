package com.example.amusic.presentation.playback

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.session.MediaController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.amusic.MainActivity
import com.example.amusic.R
import com.example.amusic.core.util.appComponent
import com.example.amusic.presentation.model.TrackUi
import com.example.amusic.presentation.playback.viewmodel.PlaybackViewModel
import com.example.amusic.presentation.playback.viewmodel.PlaybackViewModelFactory
import com.example.amusic.ui.theme.AMusicTheme
import java.util.Locale
import javax.inject.Inject

class PlaybackFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PlaybackViewModelFactory

    private val viewModel by viewModels<PlaybackViewModel> { viewModelFactory }

    private val args by navArgs<PlaybackFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AMusicTheme {
                PlaybackScreen(
                    trackUi = viewModel.currentTrack.collectAsStateWithLifecycle().value,
                    (activity as MainActivity).mediaController!!,
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTrackById(args.trackId)
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlaybackScreen(
    trackUi: TrackUi?,
    mediaController: MediaController,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    //val playerState by mediaController.playerStateFlow.collectAsState()

    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val primaryColor = AMusicTheme.colorScheme.primary

    LaunchedEffect(mediaController, lifecycleOwner.lifecycle) {
        snapshotFlow { mediaController.currentPosition }
            .collect { position ->
                sliderPosition = position.toFloat()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GlideImage(
            model = trackUi?.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(200.dp)
                .background(AMusicTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = trackUi?.title.orEmpty(),
            style = AMusicTheme.typography.title1,
            color = AMusicTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = trackUi?.author.orEmpty(),
            style = AMusicTheme.typography.body1,
            color = AMusicTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.size(16.dp))

        Slider(
            value = sliderPosition,
            onValueChange = { newValue ->
                sliderPosition = newValue
                mediaController.seekTo(newValue.toLong())
            },
            valueRange = 0f..200000f,//mediaController.duration.toFloat(),
            modifier = Modifier.fillMaxWidth(),
            thumb = {},
            colors = SliderDefaults.colors(
                activeTrackColor = AMusicTheme.colorScheme.primary,
            )
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                formatTime(mediaController.currentPosition),
                style = AMusicTheme.typography.caption,
                color = Color.White
            )
            Text(
                formatTime(mediaController.duration),
                style = AMusicTheme.typography.caption,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            IconButton(
                onClick = { mediaController.seekToPrevious() }
                //enabled = mediaController.hasPreviousMediaItem()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_previous),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            IconButton(
                onClick = {
                    if (mediaController.isPlaying) mediaController.pause() else mediaController.play()
                }
            ) {
                Icon(
                    imageVector = if (mediaController.isPlaying) Icons.Default.MoreVert else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(128.dp).drawBehind {
                        drawCircle(primaryColor, size.maxDimension / 2f)
                    }
                )
            }

            IconButton(
                onClick = { mediaController.seekToNext() },
                //enabled = mediaController.hasNextMediaItem()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

private fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds)
}