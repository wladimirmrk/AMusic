package com.example.amusic.di

import com.example.amusic.data.repository.TrackRepository
import com.example.amusic.data.repository.TrackRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun bindTrackRepository(impl: TrackRepositoryImpl): TrackRepository
}