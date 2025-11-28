package de.timpavone1990.aiapphardway.mcp.client

import io.modelcontextprotocol.kotlin.sdk.client.Client
import io.modelcontextprotocol.kotlin.sdk.client.StdioClientTransport
import io.modelcontextprotocol.kotlin.sdk.types.Implementation
import io.modelcontextprotocol.kotlin.sdk.types.TextContent
import kotlinx.coroutines.runBlocking
import kotlinx.io.asSink
import kotlinx.io.asSource
import kotlinx.io.buffered

class McpClient : AutoCloseable {
    private val mcp: Client = Client(clientInfo = Implementation(name = "mcp-client", version = "0.1.0"))

    suspend fun connectToServer(jarPath: String) {
        val process = ProcessBuilder("java", "-jar", jarPath).start()
        val transport = StdioClientTransport(
            input = process.inputStream.asSource().buffered(),
            output = process.outputStream.asSink().buffered()
        )

        mcp.connect(transport)
        println("Connected to server")


        val name = "Alice"
        println("Calling tool 'get_person_info' with argument name='$name'")
        val result = mcp.callTool(
            name = "get_person_info",
            arguments = mapOf("name" to name)
        ).content.joinToString("\n") {
            (it as TextContent).text
        }
        println("Result:\n$result")
    }


    override fun close() {
        runBlocking {
            mcp.close()
        }
    }
}
