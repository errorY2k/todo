package todo.example.app.edit;

import javax.inject.Inject;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import todo.example.app.datasource.TodoDataSource;
import todo.example.app.model.Todo;

public class TodoEditService extends Service<Void> {
	
	@Inject private TodoDataSource datasource;

//	private Todo updateTodo = null;
	private Todo editTodo = new Todo();
	private boolean updateMode = false;

	public void setUpdateTodo(Todo todo) {
//		this.updateTodo = todo;
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
		datasource.saveNewTodo(editTodo);
	}
	
	public Todo getTodo() {
		return editTodo;
	}

}
