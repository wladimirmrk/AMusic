package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class SearchTracksApiResponse(
    @SerializedName("data") val tracks: List<TrackApiModel>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val nextPage: String?
)