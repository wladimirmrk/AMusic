package com.example.amusic.presentation.chart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amusic.data.repository.TrackRepository
import com.example.amusic.presentation.model.TrackUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChartViewModel(
    private val trackRepository: TrackRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _trackListUi: MutableStateFlow<List<TrackUi>> = MutableStateFlow(emptyList())
    val trackListUi = _trackListUi.asStateFlow()

    fun search(query: String) {
        _query.update { query }
    }

    fun getChart() {
        viewModelScope.launch {
            val chart = trackRepository.getChart()
            _trackListUi.update { chart.map { track ->
                TrackUi(
                    id = track.id,
                    title = track.title,
                    author = track.author,
                    imageUrl = track.imageUrl,
                    isDownloaded = false
                )
            } }
        }
    }
}