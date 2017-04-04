 
package todo.example.app.handler;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import todo.example.app.TodoConstants;
import todo.example.app.datasource.local.model.Todo;
import todo.example.app.delete.TodoDeleteService;

public class DeleteHandler {
	
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