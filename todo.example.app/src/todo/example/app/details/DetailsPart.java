package todo.example.app.details;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.fx.core.di.LocalInstance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class DetailsPart {
	
	@FXML
	private Label todoTitleLabel;
	@FXML
	private Label detailsLabel;
	@FXML
	private Label dueDateLabel;
	
	@PostConstruct
	public void initUI(BorderPane parent, @LocalInstance FXMLLoader loader) {
		loader.setLocation(getClass().getResource("DetailsView.fxml"));
		try {
			parent.setCenter((Node)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			// ...
		}
	}
	
}
