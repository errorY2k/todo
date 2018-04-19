package todo.example.app.overview;

import javax.inject.Inject;

import org.eclipse.fx.core.di.ContextValue;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import todo.example.app.TodoConstants;
import todo.example.app.datasource.local.TodoDataSource;
import todo.example.app.datasource.local.model.Todo;

public class OverViewController {

	@FXML
	private TableView<Todo> todoTable;
	@FXML
	private TableColumn<Todo, String> todoTitleColumn;

	@Inject
	@ContextValue(value = TodoConstants.SELECTED_TODO_ITEM)
	Property<Todo> selectedTodo;

//	@Inject
	private TodoDataSource data = new TodoDataSource();
	
	@FXML
	private void initialize() {

		todoTitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		todoTable.setItems(data.getTodoList());

		selectedTodo.bind(todoTable.getSelectionModel().selectedItemProperty());

	}
}
