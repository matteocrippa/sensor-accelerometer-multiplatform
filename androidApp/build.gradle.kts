plugins {
    id("com.android.application")
    kotlin("android")
}

val coroutinesVersion = "1.6.0-native-mt"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "it.matteocrippa.sensorsmultiplatform.android"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "0.0.2"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}