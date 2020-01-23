package sample

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import arch.AndroidCommonData
import arch.ResourceView
import arch.ResourceViewObserver
import arch.android
import business.TodoListHolder
import business.TodosViewModel

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Androids"
}

class MainActivity : AppCompatActivity() {

    val viewModel = TodosViewModel(AndroidCommonData())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Sample().checkMe()
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.main_text).text = hello()

        viewModel.todoData.android().data.observe(this, ResourceViewObserver(todoView))
        viewModel.loadTodos()

    }

    val todoView = object: ResourceView<TodoListHolder> {
        override fun showData(data: TodoListHolder) {
            Toast.makeText(this@MainActivity, "loaded  ${data.todos.size} todos", Toast.LENGTH_LONG).show()
        }

        override fun showLoading(isLoading: Boolean) {
            if (isLoading) {
                Toast.makeText(this@MainActivity, "loading todos", Toast.LENGTH_LONG).show()
            }
        }

        override fun showError(error: Throwable) {
            Toast.makeText(this@MainActivity, "loading todos failed: ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}