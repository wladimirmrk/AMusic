package com.example.amusic.presentation.chart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amusic.data.repository.TrackRepository
import com.example.amusic.data.repository.model.Track
import com.example.amusic.presentation.model.TrackUi
import com.example.amusic.presentation.model.mapper.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ChartViewModel(
    private val trackRepository: TrackRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _trackListUi: MutableStateFlow<List<TrackUi>> = MutableStateFlow(emptyList())
    val trackListUi = _trackListUi.asStateFlow()

    init {
        listenToSearchQuery()
    }

    private fun listenToSearchQuery() {
        _query
            .debounce(500L)
            .mapLatest {
                if (it.isEmpty()) {
                    trackRepository.getChart()
                } else {
                    trackRepository.search(it)
                }
            }
            .flowOn(Dispatchers.Default)
            .onEach {
                _trackListUi.value = it.map(Track::toUi)
            }
            .launchIn(viewModelScope)
    }

    fun search(query: String) {
        _query.update { query }
    }
}