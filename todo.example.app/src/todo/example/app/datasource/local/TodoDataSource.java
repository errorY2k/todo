package todo.example.app.datasource.local;

import java.time.LocalDate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import todo.example.app.datasource.local.model.Todo;

public class TodoDataSource{
	
	private ObservableList<Todo> todoList = FXCollections.observableArrayList();
	
	public TodoDataSource() {
		for (int i = 0; i < 5 ; i++) {
			Todo item = new Todo();
			item.setId(i);
			item.setTitle("Title"+i);
			item.setDetails("Details"+i);
			item.setDueDate(LocalDate.now());
			todoList.add(item);
		}
	}
	
	public ObservableList<Todo> getTodoList(){
		return todoList;
	}

	private int findTodoInList(Todo _changedTodo) {
		for (Todo todo : todoList) {
			if(todo.getId() == _changedTodo.getId()){
				return todoList.indexOf(todo);
			}
		}
		return -1;
	}
	
	public void deleteTodo(Todo _todo) {
		Todo todo = find(_todo.getId());
		delete(todo);
		
		int i = findTodoInList(_todo);
		Platform.runLater(() -> {
			todoList.remove(i);
		});
	}

	private void delete(Todo todo) {
		// TODO Auto-generated method stub
		
	}

	private Todo find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Todo editTodo) {
		// TODO Auto-generated method stub
		
	}

	public void saveNewTodo(Todo editTodo) {
		// TODO Auto-generated method stub
		
	}
}
