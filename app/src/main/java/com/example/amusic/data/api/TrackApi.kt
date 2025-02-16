package com.example.amusic.data.api

import com.example.amusic.data.api.model.ChartApiResponse
import com.example.amusic.data.api.model.SearchTracksApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackApi {

    @GET("/chart")
    suspend fun getChartTracks(): ChartApiResponse

    @GET("/search")
    suspend fun getTracksByQuery(@Query("q") query: String): SearchTracksApiResponse
}