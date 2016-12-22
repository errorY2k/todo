package todo.example.app.datasource.internal;

import java.time.LocalDate;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import todo.example.app.datasource.TodoDataSource;
import todo.example.app.model.Todo;

@Component
public class TodoDataSourceImpl implements TodoDataSource {
	
	private ObservableList<Todo> todoList = FXCollections.observableArrayList();

	@Activate
	void activate() {
		System.out.println("ACTIVATE");
		initTestData();
	}

	private void initTestData() {
		todoList.add(new Todo("TODO_1", "DETAILS_1", LocalDate.now()));
		todoList.add(new Todo("TODO_2", "DETAILS_2", LocalDate.of(2016, 12, 31)));
		todoList.add(new Todo("TODO_3", "DETAILS_3", LocalDate.of(2017, 12, 31)));
		todoList.add(new Todo("TODO_4", "DETAILS_4", LocalDate.now()));
	}
	
	@Override
	public ObservableList<Todo> getTodoList(){
		return todoList;
	}

	@Override
	public void update(Todo updateTodo, Todo changedTodo) {
		Platform.runLater(() -> {
			updateTodo.setTitle(changedTodo.getTitle());
			updateTodo.setDetails(changedTodo.getDetails());
			updateTodo.setDueDate(changedTodo.getDueDate());
		});
	}

	@Override
	public void saveNewTodo(Todo newTodo) {
		Platform.runLater(() -> {
			todoList.add(newTodo);
		});
	}
}
