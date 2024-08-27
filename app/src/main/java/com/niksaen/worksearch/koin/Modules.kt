package com.niksaen.worksearch.koin

import android.content.Context
import com.niksaen.worksearch.localdatamanager.LocalDbManager
import com.niksaen.worksearch.accessToApi.AccessToApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val module = module {
    single<Context> { androidApplication().applicationContext }
    single<AccessToApi> { AccessToApi() }
    single<LocalDbManager>{ LocalDbManager(androidApplication()) }
}