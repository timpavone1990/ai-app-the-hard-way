plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.shadow.gradle.plugin)
}

kotlin {
    kotlin {
        jvmToolchain(21)
    }
}
