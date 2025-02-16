package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class ChartApiResponse(
    @SerializedName("tracks") val tracks: TracksData,
    @SerializedName("albums") val albums: ContentData<Album>,
    @SerializedName("artists") val artists: ContentData<Artist>,
    @SerializedName("playlists") val playlists: ContentData<Playlist>,
    @SerializedName("podcasts") val podcasts: ContentData<Podcast>
)
