package com.example.amusic.data.repository

import com.example.amusic.data.api.TrackApi
import com.example.amusic.data.repository.model.Track
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val trackApi: TrackApi
) : TrackRepository {

    override suspend fun getChart(): List<Track> {
        return trackApi.getChartTracks().tracks.items.map { trackApiModel ->
            Track(
                id = trackApiModel.id,
                imageUrl = trackApiModel.album.cover,
                title = trackApiModel.title,
                author = trackApiModel.artist.name,
                audioUrl = trackApiModel.previewUrl
            )
        }
    }
}