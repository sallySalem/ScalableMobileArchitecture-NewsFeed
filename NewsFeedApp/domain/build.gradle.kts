plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.example.domain"
version = "1.0"

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.core)
    implementation(libs.paging.common)
}
