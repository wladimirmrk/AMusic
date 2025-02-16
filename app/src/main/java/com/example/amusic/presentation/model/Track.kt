package com.example.amusic.presentation.model

data class Track(
    val id: Int = -1,
    val title: String = "Title",
    val author: String = "Author",
    val imageUrl: String = "",
    val isDownloaded: Boolean = false
)
