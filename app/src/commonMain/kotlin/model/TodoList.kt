package model

import kotlinx.serialization.Serializable

@Serializable
class TodoList (
    var todo_entries: List<Todo>
)