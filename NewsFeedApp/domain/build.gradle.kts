plugins {
    id("org.jetbrains.kotlin.jvm")
}

group = "com.example.domain"
version = "1.0"

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("com.google.dagger:hilt-core:2.48")
    implementation("androidx.paging:paging-common:3.3.0-alpha02")
}
