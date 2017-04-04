package todo.example.app.edit;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import todo.example.app.datasource.local.TodoDataSource;
import todo.example.app.datasource.local.model.Todo;

public class TodoEditService extends Service<Void> {
	
	private TodoDataSource datasource = new TodoDataSource();

//	private Todo updateTodo = null;
	private Todo editTodo = new Todo();
	private boolean updateMode = false;

	public void setUpdateTodo(Todo todo) {
		editTodo = todo.copy();
		updateMode  = true;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if (updateMode) {
					updateTodo();
				} else {
					saveNewTodo();
				}
				return null;
			}
		};
	}
	
	private void updateTodo(){
		System.out.println("update! ");
		datasource.update(editTodo);
	}
	
	private void saveNewTodo() {
		System.out.println("new todo! " + editTodo);
		datasource.saveNewTodo(editTodo);
	}
	
	public Todo getTodo() {
		return editTodo;
	}

}
