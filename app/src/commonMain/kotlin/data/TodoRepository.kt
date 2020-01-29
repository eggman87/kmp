package data

import kotlinx.coroutines.delay
import kotlinx.serialization.UnstableDefault
import model.Todo
import network.Api

@UseExperimental(UnstableDefault::class)
class TodoRepository  constructor(private val api:Api = Api()) : Repository() {

    suspend fun getTodos(): List<Todo> {
        delay(3000)
        return api.
            getTodoList()
    }
}