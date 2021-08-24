package com.knotes.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        //setTheme()
    }

    companion object{
        private lateinit var appContext:Context
        fun getContext(): Context{
            return appContext
        }
    }
}