package com.niksaen.worksearch

import android.app.Application
import com.niksaen.worksearch.koin.module
import com.niksaen.worksearch.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WorkSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@WorkSearchApp)
            modules(module, viewModelModule)
        }
    }
}