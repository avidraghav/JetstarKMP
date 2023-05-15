plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    id("com.github.gmazzo.buildconfig") apply false
    id("com.squareup.sqldelight") version "1.5.5" apply false
}
