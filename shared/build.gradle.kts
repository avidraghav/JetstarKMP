import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.github.gmazzo.buildconfig")
    kotlin("plugin.serialization") version "1.8.20"
    id("kotlin-parcelize")
}

kotlin {
    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
            export("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.0-compose-experimental-alpha-02")
            export("com.arkivanov.essenty:parcelable:1.1.0")
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    val coroutinesVersion = "1.6.4"
    val ktorVersion = "2.2.4"
    val koinVersion = "3.2.0"
    val napierVersion = "2.6.1"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
                implementation("io.ktor:ktor-client-logging:2.0.3")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.insert-koin:koin-test:$koinVersion")
                api("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.0-compose-experimental-alpha-02")
                api("com.arkivanov.essenty:parcelable:1.1.0")
                implementation("io.github.aakira:napier:$napierVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")

                implementation("io.insert-koin:koin-android:$koinVersion")
                implementation("io.coil-kt:coil-compose:2.3.0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.raghav.jetstar.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

buildConfig {
    buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
}
