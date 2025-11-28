package de.timpavone1990.aiapphardway.mcp.server

class PersonRepository {
    fun getInformationAbout(name: String): String {
        return """
            firstName: $name
            lastName: Doe
            birthDate: 1990-01-01
        """.trimIndent()
    }
}
