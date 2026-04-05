plugins {
    id("com.android.library")
    alias(libs.plugins.legacy.kapt)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }
    testOptions {
        targetSdk = 36
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    kapt(libs.room.compiler)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.paging.runtime)
    implementation(libs.paging.common)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}
