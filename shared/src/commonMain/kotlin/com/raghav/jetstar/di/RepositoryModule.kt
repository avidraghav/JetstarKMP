package com.raghav.jetstar.di

import com.raghav.jetstar.data.repository.HomeScreenRepositoryImpl
import com.raghav.jetstar.data.sources.MovieServiceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        HomeScreenRepositoryImpl(get<MovieServiceImpl>())
    }
}
