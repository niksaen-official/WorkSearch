package com.niksaen.worksearch.koin

import com.niksaen.worksearch.viewModels.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<MainViewModel> { MainViewModel() }
}