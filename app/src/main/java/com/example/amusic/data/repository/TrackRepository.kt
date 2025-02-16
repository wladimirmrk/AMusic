package com.example.amusic.data.repository

import com.example.amusic.data.repository.model.Track

interface TrackRepository {

    suspend fun getChart(): List<Track>
}