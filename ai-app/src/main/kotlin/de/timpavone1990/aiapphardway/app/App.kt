package de.timpavone1990.aiapphardway.app

import de.timpavone1990.aiapphardway.mcp.client.McpClient
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    if (args.isEmpty()) {
        throw IllegalArgumentException("Please provide the path to the JAR file as a command-line argument.")
    }
    val jarPath = args[0]
    val client = McpClient()
    client.use {
        client.connectToServer(jarPath)
    }
}
