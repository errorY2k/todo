package todo.example.app.details;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TodoDetailsController {

//	 @Inject
//	 private ESelectionService selectionService;

	@FXML
	private Label todoTitleLabel;
	@FXML
	private Label detailsTextLabel;
	@FXML
	private Label dueDateLabel;

//	@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo;

//	public TodoDetailsController() {
//	}

	@FXML
	private void initialize() {
		reset();
	//		selectionService.addSelectionListener((part, selection) -> {
	//			Todo todo = (selection instanceof Todo) ? (Todo) selection : null;
	//			System.out.println(todo);
	//			setCurrentTodo(todo);
	//		});
	}
	
//	@Execute
//	private void execute(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
//		if (todo != null) {
//			todoTitleLabel.textProperty().bind(todo.titleProperty());
//			detailsTextLabel.textProperty().bind(todo.detailsProperty());
//			dueDateLabel.textProperty().bind(todo.dueDateProperty().asString());
//		} else {
//			todoTitleLabel.textProperty().unbind();
//			detailsTextLabel.textProperty().unbind();
//			dueDateLabel.textProperty().unbind();
//			reset();
//		}
//	}
//	
//	@CanExecute
//		public boolean canExecute(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
//		return todo != null;
//	}
	
//	@Inject
//	private void setCurrentTodo(@Named(TodoConstants.SELECTED_TODO_ITEM) Todo todo) {
//		if (todo != null) {
//			todoTitleLabel.textProperty().bind(todo.titleProperty());
//			detailsTextLabel.textProperty().bind(todo.detailsProperty());
//			dueDateLabel.textProperty().bind(todo.dueDateProperty().asString());
//		} else {
//			todoTitleLabel.textProperty().unbind();
//			detailsTextLabel.textProperty().unbind();
//			dueDateLabel.textProperty().unbind();
//			reset();
//		}
//	}

	private void reset() {
		todoTitleLabel.setText("Select");
		dueDateLabel.setText("an Item");
		detailsTextLabel.setText("to display");
	}
}
