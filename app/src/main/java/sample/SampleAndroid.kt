package sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import arch.AndroidCommonData
import arch.ResourceView
import arch.ResourceViewObserver
import arch.android
import business.TodoListHolder
import business.TodosViewModel
import kotlinx.android.synthetic.main.activity_main.*
import model.Todo

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity() {

    val viewModel = TodosViewModel(AndroidCommonData())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Sample().checkMe()
        setContentView(R.layout.activity_main)

        viewModel.todoData.android().data.observe(this, ResourceViewObserver(todoView))
        viewModel.loadTodos()

        rvTodos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTodos.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        supportActionBar?.title = hello()
    }

    private val todoView = object: ResourceView<TodoListHolder> {
        override fun showData(data: TodoListHolder) {
            rvTodos.adapter = TodoAdapter(data.todos)
        }

        override fun showLoading(isLoading: Boolean) {
            todoProgress.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        override fun showError(error: Throwable) {
            Toast.makeText(this@MainActivity, "loading todos failed: ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}

class TodoAdapter(private val todos: List<Todo>): RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)  = holder.bind(todos[position])
}

class TodoViewHolder(itemView: View): RecyclerView.ViewHolder (itemView) {

    private val txtTitle = itemView.findViewById<TextView>(R.id.view_todo_item)

    fun bind(todo: Todo) {
        txtTitle.text = todo.title
    }
}