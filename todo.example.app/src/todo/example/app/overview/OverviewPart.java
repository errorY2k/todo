package todo.example.app.overview;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.fx.core.di.LocalInstance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class OverviewPart {


	@PostConstruct
	public void initUI(BorderPane parent, @LocalInstance FXMLLoader loader) {
		loader.setLocation(getClass().getResource("Overview.fxml"));
		try {
			parent.setCenter((Node)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			// ...
		}	
	}
}
