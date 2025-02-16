package com.example.amusic.data.repository

import com.example.amusic.data.api.TrackApi
import com.example.amusic.data.api.model.TrackApiModel
import com.example.amusic.data.repository.mapper.toTrack
import com.example.amusic.data.repository.model.Track
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val trackApi: TrackApi
) : TrackRepository {

    override suspend fun getChart(): List<Track> {
        return trackApi.getChartTracks().tracks.items.map(TrackApiModel::toTrack)
    }

    override suspend fun search(query: String): List<Track> {
        return trackApi.getTracksByQuery(query).tracks.map(TrackApiModel::toTrack)
    }
}