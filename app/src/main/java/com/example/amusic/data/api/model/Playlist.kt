package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("picture") val picture: String
)