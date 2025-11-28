plugins {
    id("buildlogic.kotlin-application-conventions")
    id("com.gradleup.shadow") version "9.2.2"
}

dependencies {
    implementation(libs.mcp.kotlin.sdk)
}

application {
    mainClass = "de.timpavone1990.aiapphardway.mcp.server.AppKt"
}
