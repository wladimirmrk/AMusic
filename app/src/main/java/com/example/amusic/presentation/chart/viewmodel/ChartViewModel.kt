package com.example.amusic.presentation.chart.viewmodel

import androidx.lifecycle.ViewModel
import com.example.amusic.presentation.components.trackUis
import com.example.amusic.presentation.model.TrackUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChartViewModel : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _trackListUi: MutableStateFlow<List<TrackUi>> = MutableStateFlow(emptyList())
    val trackListUi = _trackListUi.asStateFlow()

    fun search(query: String) {
        _query.update { query }
    }

    fun getChart() {
        _trackListUi.update { trackUis }
    }
}