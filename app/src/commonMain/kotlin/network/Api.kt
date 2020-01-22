package network

import network.ApplicationDispatcher
import io.ktor.client.HttpClient
import kotlinx.coroutines.GlobalScope
import io.ktor.client.request.get
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import model.Todo
import model.TodoList

class Api {

    private val httpClient = HttpClient()

    fun getTodoList(success: (List<Todo>) -> Unit, failure: (Throwable?) -> Unit){
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val url = "https://jsonplaceholder.typicode.com/todos"
                val json = httpClient.get<String>(url)

                // An example of failed Json parsing. Show debugging from iOS side.
//                Json.nonstrict.parse(TodoList.serializer(), json)
//                    .todo_entries
//                    .also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }
}