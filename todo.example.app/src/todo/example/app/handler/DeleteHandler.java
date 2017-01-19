 
package todo.example.app.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import todo.example.app.TodoConstants;
import todo.example.app.datasource.TodoDataSource;
import todo.example.app.delete.TodoDeleteService;
import todo.example.app.model.Todo;

public class DeleteHandler {
	
	@Inject	private TodoDataSource data;
	
	@Execute
	public void execute(IEclipseContext context, @Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
		IEclipseContext child = context.createChild("TodoDeleteContext");
		try {
			TodoDeleteService service = ContextInjectionFactory.make(TodoDeleteService.class, child);
			service.setDeleteTodo(todo);
			child.set(TodoDeleteService.class, service);
			service.start();
		} finally {
			child.dispose();
		}
	}
	
	@CanExecute
	public boolean canExecute(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
		return todo != null;
	}

		
}