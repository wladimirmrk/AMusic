package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class TracksData(
    @SerializedName("data") val items: List<TrackApiModel>,
    @SerializedName("total") val total: Int
)