package todo.example.app.delete;

import javax.inject.Inject;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import todo.example.app.datasource.TodoDataSource;
import todo.example.app.model.Todo;

public class TodoDeleteService extends Service<Void>{

	@Inject private TodoDataSource datasource;
	
	Todo deleteTodo = null;
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				deleteTodo();
				return null;
			}
		};
	}

	private void deleteTodo(){
		System.out.println("delete");
		datasource.deleteTodo(deleteTodo);
	}

	public Todo getDeleteTodo() {
		return deleteTodo;
	}

	public void setDeleteTodo(Todo deleteTodo) {
		this.deleteTodo = deleteTodo;
	}

}
