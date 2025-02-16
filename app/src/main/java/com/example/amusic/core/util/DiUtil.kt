package com.example.amusic.core.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.example.amusic.app.App

// extension property for Activity
val Activity.appComponent get() = (applicationContext as App).appComponent

// extension property for Fragment
val Fragment.appComponent get() = (requireContext().applicationContext as App).appComponent