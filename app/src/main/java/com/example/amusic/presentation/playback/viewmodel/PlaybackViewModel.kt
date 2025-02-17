package com.example.amusic.presentation.playback.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amusic.data.repository.TrackRepository
import com.example.amusic.presentation.model.TrackUi
import com.example.amusic.presentation.model.mapper.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaybackViewModel(
    private val trackRepository: TrackRepository
) : ViewModel() {

    private val _currentTrack: MutableStateFlow<TrackUi?> = MutableStateFlow(null)
    val currentTrack = _currentTrack.asStateFlow()

    fun getTrackById(id: Long) {
        viewModelScope.launch {
            _currentTrack.update { trackRepository.getTrackById(id)?.toUi() }
        }
    }
}