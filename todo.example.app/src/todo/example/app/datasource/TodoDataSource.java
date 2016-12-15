package todo.example.app.datasource;

import java.time.LocalDate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import todo.example.app.model.Todo;

public class TodoDataSource {

	private ObservableList<Todo> todoList = FXCollections.observableArrayList();
	
	public TodoDataSource() {
		todoList.add(new Todo("TODO_1", "DETAILS_1", LocalDate.now()));
		todoList.add(new Todo("TODO_2", "DETAILS_2", LocalDate.of(2016, 12, 31)));
		todoList.add(new Todo("TODO_3", "DETAILS_3", LocalDate.of(2017, 12, 31)));
		todoList.add(new Todo("TODO_4", "DETAILS_4", LocalDate.now()));
	}
	
	public ObservableList<Todo> getTodoList(){
		return todoList;
	}

	public void update(Todo updateTodo, Todo changedTodo) {
		Platform.runLater(() -> {
			updateTodo.setTitle(changedTodo.getTitle());
			updateTodo.setDetails(changedTodo.getDetails());
			updateTodo.setDueDate(changedTodo.getDueDate());
		});
	}

	public void saveNewTodo(Todo newTodo) {
		Platform.runLater(() -> {
			todoList.add(newTodo);
		});
		
	}
}
