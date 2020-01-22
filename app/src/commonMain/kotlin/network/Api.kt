package network

import io.ktor.client.HttpClient
import kotlinx.coroutines.GlobalScope
import io.ktor.client.request.get
import kotlinx.coroutines.*
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlinx.serialization.parse
import model.Todo

@Suppress("NO_ACTUAL_FOR_EXPECT")
@UnstableDefault
class Api {

    private val httpClient = HttpClient()
    private val url = "https://jsonplaceholder.typicode.com/todos"

    @UnstableDefault
    fun getTodoList(success: (List<Todo>) -> Unit, failure: (Throwable?) -> Unit){
        GlobalScope.launch(ApplicationDispatcher) {
            try {

                val json = httpClient.get<String>(url)

                Json.nonstrict.parse(Todo.serializer().list, json)
                    .also(success)

                // An example of failed Json parsing. To show debugging on iOS side.
//                Json.nonstrict.parse(TodoList.serializer(), json)
//                    .todo_entries
//                    .also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

    suspend fun getTodoList(): List<Todo> {
        val json = httpClient.get<String>(url)
        return Json.nonstrict.parse(Todo.serializer().list, json).toList()
    }
}