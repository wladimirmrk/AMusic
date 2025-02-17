package com.example.amusic.data.repository

import com.example.amusic.data.repository.model.Track

interface TrackRepository {

    suspend fun getChart(): List<Track>

    suspend fun search(query: String): List<Track>

    suspend fun getTrackById(id: Long): Track?
}