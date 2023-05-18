package com.raghav.jetstar

import android.app.Application
import android.content.Context
import com.raghav.jetstar.di.initKoin
import org.koin.android.ext.koin.androidContext

class JetstarApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initKoin {
            androidContext(applicationContext)
        }
    }
}
