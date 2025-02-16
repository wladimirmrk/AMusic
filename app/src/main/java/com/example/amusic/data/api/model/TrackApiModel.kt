package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class TrackApiModel(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("title_short") val titleShort: String,
    @SerializedName("title_version") val titleVersion: String?,
    @SerializedName("duration") val duration: Int,
    @SerializedName("preview") val previewUrl: String,
    @SerializedName("artist") val artist: Artist,
    @SerializedName("album") val album: Album
)