 
package todo.example.app.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import todo.example.app.TodoConstants;
import todo.example.app.datasource.TodoDataSource;
import todo.example.app.model.Todo;

public class DeleteHandler {
	
	@Inject	private TodoDataSource data;
	
	@Execute
	public void execute(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
		if(todo != null){
			data.getTodoList().remove(todo);
		}
	}
	
	@CanExecute
	public boolean canExecute(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
		return todo != null;
	}

		
}