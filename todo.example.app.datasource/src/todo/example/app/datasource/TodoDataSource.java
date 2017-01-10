package todo.example.app.datasource;

import javafx.collections.ObservableList;
import todo.example.app.model.Todo;

public interface TodoDataSource {

	ObservableList<Todo> getTodoList();

//	void update(Todo updateTodo, Todo changedTodo);

	void saveNewTodo(Todo newTodo);

	void deleteTodo(Todo todo);

	void update(Todo _changedTodo);
}
