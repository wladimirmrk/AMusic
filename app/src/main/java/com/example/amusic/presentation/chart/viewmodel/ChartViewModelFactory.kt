package com.example.amusic.presentation.chart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.amusic.data.repository.TrackRepository
import javax.inject.Inject

class ChartViewModelFactory @Inject constructor(
    private val trackRepository: TrackRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChartViewModel(trackRepository) as T
    }
}