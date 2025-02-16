package com.example.amusic.data.api

import com.example.amusic.data.api.model.ChartApiResponse
import retrofit2.http.GET

interface TrackApi {

    @GET("/chart")
    suspend fun getChartTracks(): ChartApiResponse

}