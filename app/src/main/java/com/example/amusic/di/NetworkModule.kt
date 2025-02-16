package com.example.amusic.di

import com.example.amusic.data.api.TrackApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.deezer.com/"

@Module
interface NetworkModule {

    companion object {

        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        @Provides
        fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        fun provideTrackApi(retrofit: Retrofit): TrackApi = retrofit.create(TrackApi::class.java)
    }
}