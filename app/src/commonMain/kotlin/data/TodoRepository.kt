package data

import kotlinx.serialization.UnstableDefault
import model.Todo
import network.Api

@UseExperimental(UnstableDefault::class)
class TodoRepository  constructor(private val api:Api = Api()) : Repository() {

    suspend fun getTodos(): List<Todo> {
        return api.getTodoList()
    }
}