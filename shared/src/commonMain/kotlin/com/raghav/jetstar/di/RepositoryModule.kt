package com.raghav.jetstar.di

import com.raghav.jetstar.data.repository.MovieRepositoryImpl
import com.raghav.jetstar.data.sources.MovieServiceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MovieRepositoryImpl(get<MovieServiceImpl>())
    }
}
