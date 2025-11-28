package de.timpavone1990.aiapphardway.mcp.server

import io.ktor.utils.io.streams.*
import io.modelcontextprotocol.kotlin.sdk.server.Server
import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions
import io.modelcontextprotocol.kotlin.sdk.server.StdioServerTransport
import io.modelcontextprotocol.kotlin.sdk.types.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.io.asSink
import kotlinx.io.buffered
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.putJsonObject

fun startServer() {
    val server = Server(
        Implementation(name = "People MCP Server", version = "0.1.0"),
        ServerOptions(
            capabilities = ServerCapabilities(tools = ServerCapabilities.Tools(listChanged = true))
        )
    )
    val transport = StdioServerTransport(System.`in`.asInput(), System.out.asSink().buffered())

    val personRepository = PersonRepository()
    server.addTool(
        name = "get_person_info",
        description = "Get information about a person given their name",
        inputSchema = ToolSchema(
            properties = buildJsonObject {
                putJsonObject("name") {
                    put("type", JsonPrimitive("string"))
                }
            },
            required = listOf("name")
        )
    ) {
            request ->
        val name = request.arguments?.get("name")?.jsonPrimitive?.content
        if (name == null) {
            CallToolResult(content = listOf(TextContent("The parameter 'name' is required.")))
        } else {
            CallToolResult(content = listOf(TextContent(personRepository.getInformationAbout(name))))
        }
    }

    println("Starting MCP Server...")
    runBlocking {
        server.createSession(transport)
        val done = Job()
        server.onClose { done.complete() }
        done.join()
    }
}
