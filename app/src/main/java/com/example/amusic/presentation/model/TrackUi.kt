package com.example.amusic.presentation.model

data class TrackUi(
    val id: Long = -1,
    val title: String = "Title",
    val author: String = "Author",
    val imageUrl: String = "",
    val isDownloaded: Boolean = false
)
