package todo.example.app.edit;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import todo.example.app.datasource.local.model.Todo;

public class TodoEditController {

	@Inject 
	private TodoEditService service;

	@FXML
	private TextField todoTitleField;
	@FXML
	private TextField todoDetailsField;
	@FXML
	private DatePicker dueDate;

	@FXML
	private void initialize() {
		Todo copy = service.getTodo();
		todoTitleField.textProperty().bindBidirectional(copy.titleProperty());
		todoDetailsField.textProperty().bindBidirectional(copy.detailsProperty());
		dueDate.valueProperty().bindBidirectional(copy.dueDateProperty());
	}

}
