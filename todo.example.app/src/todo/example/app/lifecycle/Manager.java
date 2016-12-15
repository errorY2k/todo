package todo.example.app.lifecycle;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;

import todo.example.app.datasource.TodoDataSource;

public class Manager {


	
	@PostContextCreate
	void postContextCreate(IEclipseContext context) {
		
		context.set(TodoDataSource.class, new TodoDataSource());

	}
	

}
