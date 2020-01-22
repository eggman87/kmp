package business

import data.TodoRepository
import kotlinx.coroutines.launch

class TodosViewModel(val repo: TodoRepository) : ViewModel(){

    fun loadTodos() {
        launch {
            val todos = repo.getTodos()
        }
    }
}