package com.raghav.jetstar.util

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual fun initLogger() {
    Napier.base(DebugAntilog())
}
