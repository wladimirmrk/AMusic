package com.example.amusic.data.api.model

import com.google.gson.annotations.SerializedName

data class ContentData<T>(
    @SerializedName("data") val items: List<T>,
    @SerializedName("total") val total: Int
)
