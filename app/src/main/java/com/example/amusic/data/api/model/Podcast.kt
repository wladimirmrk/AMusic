package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)