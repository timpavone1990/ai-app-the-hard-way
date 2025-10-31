# AI App The Hard Way
This is a learning project for AI applications.
Everything is _intentionally_ implemented manually and without the help of high-level frameworks or libraries,
so that it’s possible to learn the basics and details that such tools usually abstract away.

This project contains three parts:
1. A command to run a LLM locally using Ollama.
2. An MCP server that provides information about individuals that cannot be known to publicly available LLMs.
   It retrieves this information from files in the local file system.
3. An application that reads user queries through the command line, feeds them to the locally running LLM, and prints its response to the command line. It also implements an MCP client for the above MCP server so that the LLM has access to information about persons mentioned in the query.

