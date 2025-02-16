package com.example.amusic.presentation.model.mapper

import com.example.amusic.data.repository.model.Track
import com.example.amusic.presentation.model.TrackUi

fun Track.toUi() = TrackUi(
    id = this.id,
    title = this.title,
    author = this.author,
    imageUrl = this.imageUrl,
    isDownloaded = false
)