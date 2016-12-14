package todo.example.app.edit;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.fx.core.di.LocalInstance;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import todo.example.app.model.Todo;

public class TodoEditDialog extends Dialog<Todo> {

	// @Inject @LocalInstance FXMLLoader loader;

	// @Inject
	// public TodoEditDialog(@LocalInstance FXMLLoader loader) {
	// loader.setLocation(getClass().getResource("TodoEditView.fxml"));
	// try {
	// getDialogPane().setContent((Node)loader.load());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// init();
	// }
	
	@Inject private TodoEditService todoEditService;

	@PostConstruct
	public void initUI(@LocalInstance FXMLLoader loader) {
		loader.setLocation(getClass().getResource("TodoEditView.fxml"));
		try {
			getDialogPane().setContent((Node) loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}

		init();
	}

	private void init() {
		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		setTitle("Edit Todo");
		getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION,
				event -> todoEditService.start());

	}
}
