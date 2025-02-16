package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String
)
