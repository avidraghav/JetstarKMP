package com.raghav.jetstar.di

import com.raghav.jetstar.database.Database
import com.raghav.jetstar.database.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {

    single {
        Database(get<DatabaseDriverFactory>())
    }

    single {
        DatabaseDriverFactory()
    }
}
