package com.example.amusic.app

import android.app.Application
import com.example.amusic.di.AppComponent
import com.example.amusic.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}