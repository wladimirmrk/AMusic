package com.example.amusic.presentation.chart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.amusic.R
import com.example.amusic.core.util.appComponent
import com.example.amusic.presentation.chart.viewmodel.ChartViewModel
import com.example.amusic.presentation.chart.viewmodel.ChartViewModelFactory
import com.example.amusic.presentation.components.TrackListScreen
import com.example.amusic.ui.theme.AMusicTheme
import javax.inject.Inject


class ChartFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ChartViewModelFactory

    private val viewModel by viewModels<ChartViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AMusicTheme {
                TrackListScreen(
                    title = stringResource(R.string.chart),
                    query = viewModel.query.collectAsStateWithLifecycle().value,
                    onQueryChanged = viewModel::search,
                    trackUiList = viewModel.trackListUi.collectAsStateWithLifecycle().value,
                    onClickDownload = {},
                    onClickTrack = {}
                )
            }
        }
    }
}