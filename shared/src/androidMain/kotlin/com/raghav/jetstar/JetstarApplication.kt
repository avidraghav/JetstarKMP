package com.raghav.jetstar

import android.app.Application
import com.raghav.jetstar.di.initKoin
import org.koin.android.ext.koin.androidContext

class JetstarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}
