package network

import io.ktor.client.HttpClient
import kotlinx.coroutines.GlobalScope
import io.ktor.client.request.get
import kotlinx.coroutines.*
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlinx.serialization.parse
import log.Logger
import model.Todo

@Suppress("NO_ACTUAL_FOR_EXPECT")
@UnstableDefault
class Api {

    private val httpClient = HttpClient()
    private val url = "https://jsonplaceholder.typicode.com/todos"
    private val log = Logger()

    suspend fun getTodoList(): List<Todo> {
        try {
            log.log("API", "Making api call to $url")
            val json = httpClient.get<String>(url)
            return Json.nonstrict.parse(Todo.serializer().list, json).toList()
        } catch (execption: Exception) {
            log.logError("API", execption, "error while loading todos.")
            throw execption
        }
    }
}