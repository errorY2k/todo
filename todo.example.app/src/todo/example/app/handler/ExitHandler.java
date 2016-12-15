 
package todo.example.app.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

public class ExitHandler {
	@Execute
	public void execute() {
		System.exit(0);
	}
	
	@CanExecute
	public boolean canExecute() {
		
		return true;
	}
		
}