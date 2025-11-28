package de.timpavone1990.aiapphardway.mcp.server

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(App().greeting)
}
