package business

import data.TodoRepository
import model.Todo

/**
 * A view model will represent a way for the UI (the view) to request data, and observe that data
 * via data holders. The exact implementation of what a data holder looks like is still tbd.
 *
 * Addition, business logic would live here. Data holder observables would be the main way to communicate
 * to the UI (especially for async stuff) but perhaps the view model can expose sync functions too.
 */
class TodosViewModel(val todoData: CommonData<TodoListHolder>) : ViewModel(){

    private val repo: TodoRepository = TodoRepository()

    fun loadTodos() {
        todoData.loadCommonData({ repo.getTodos() }, { TodoListHolder(it) })
    }
}

data class TodoListHolder(val todos: List<Todo>)