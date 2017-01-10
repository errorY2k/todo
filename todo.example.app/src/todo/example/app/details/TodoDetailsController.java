package todo.example.app.details;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.fx.core.Subscription;
import org.eclipse.fx.core.di.ContextBoundValue;
import org.eclipse.fx.core.di.ContextValue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import todo.example.app.TodoConstants;
import todo.example.app.model.Todo;

public class TodoDetailsController {

	@FXML
	private Label todoTitleLabel;
	@FXML
	private Label detailsTextLabel;
	@FXML
	private Label dueDateLabel;
	
	@Inject
	@ContextValue(value = TodoConstants.SELECTED_TODO_ITEM)
	ContextBoundValue<Todo> selectedTodo;
	
	private Subscription subscribeOnValueChange;

	@FXML
	private void initialize() {
		reset();
		subscribeOnValueChange = selectedTodo.subscribeOnValueChange(todo -> setCurrentTodo(todo));
	}
	
	@PreDestroy
	void dispose() {
		subscribeOnValueChange.dispose();
	}

//	@Inject
	private void setCurrentTodo(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
		if (todo != null) {
			todoTitleLabel.textProperty().bind(todo.titleProperty());
			detailsTextLabel.textProperty().bind(todo.detailsProperty());
			dueDateLabel.textProperty().bind(todo.dueDateProperty().asString());
		} else {
			reset();
		}
	}

	private void reset() {
		todoTitleLabel.textProperty().unbind();
		detailsTextLabel.textProperty().unbind();
		dueDateLabel.textProperty().unbind();
		todoTitleLabel.setText("Select");
		dueDateLabel.setText("an Item");
		detailsTextLabel.setText("to display");
	}
}
