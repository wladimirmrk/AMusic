package com.example.amusic.data.repository

import com.example.amusic.data.api.TrackApi
import com.example.amusic.data.api.model.TrackApiModel
import com.example.amusic.data.repository.mapper.toTrack
import com.example.amusic.data.repository.model.Track
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrackRepositoryImpl @Inject constructor(
    private val trackApi: TrackApi
) : TrackRepository {

    private val trackMutex = Mutex()
    private var chart: List<Track>? = null

    override suspend fun getChart(): List<Track> {
        return trackMutex.withLock {
            if (chart == null) {
                chart = trackApi.getChartTracks().tracks.items.map(TrackApiModel::toTrack)
            }
            chart!!
        }
    }

    override suspend fun search(query: String): List<Track> {
        return trackApi.getTracksByQuery(query).tracks.map(TrackApiModel::toTrack)
    }

    override suspend fun getTrackById(id: Long): Track? {
        return chart?.find { it.id == id }
    }
}