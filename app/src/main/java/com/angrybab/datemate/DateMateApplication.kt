package com.angrybab.datemate

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DateMateApplication: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: DateMateApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}