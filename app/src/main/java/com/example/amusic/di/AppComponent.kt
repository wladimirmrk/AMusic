package com.example.amusic.di

import android.content.Context
import com.example.amusic.presentation.chart.ChartFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }

    fun inject(chartFragment: ChartFragment)
}