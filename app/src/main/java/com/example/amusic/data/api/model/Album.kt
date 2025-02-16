package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("cover") val cover: String
)