package com.example.amusic.data.repository.mapper

import com.example.amusic.data.api.model.TrackApiModel
import com.example.amusic.data.repository.model.Track

fun TrackApiModel.toTrack(): Track {
    return Track(
        id = this.id,
        imageUrl = this.album.cover,
        title = this.title,
        author = this.artist.name,
        audioUrl = this.previewUrl
    )
}