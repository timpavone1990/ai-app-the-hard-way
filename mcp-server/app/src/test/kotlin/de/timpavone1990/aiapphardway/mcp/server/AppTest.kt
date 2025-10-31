package de.timpavone1990.aiapphardway.mcp.server

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun appHasAGreeting() {
        val classUnderTest = App()
        Assertions.assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}
