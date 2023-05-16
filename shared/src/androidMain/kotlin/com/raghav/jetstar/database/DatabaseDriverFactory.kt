package com.raghav.jetstar.database

import android.content.Context
import com.raghav.jetstar.JetstarApplication
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        val context: Context = JetstarApplication.appContext
        return AndroidSqliteDriver(AppDatabase.Schema, context, "jetstar_database.db")
    }
}
