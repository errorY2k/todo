package todo.example.app.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Todo {

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty title = new SimpleStringProperty();
	private StringProperty details = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<LocalDate>();
	
	public Todo() {
	}

	public Todo(int id, String title, String details, LocalDate dueDate) {
		this.id.set(id);
		this.title.set(title);
		this.details.set(details);
		this.dueDate.set(dueDate);
	}


	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public int getId() {
		return this.idProperty().get();
	}

	public void setId(int id) {
		this.idProperty().set(id);
	}

	public final StringProperty titleProperty() {
		return this.title;
	}
	

	public final String getTitle() {
		return this.titleProperty().get();
	}
	

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	

	public final StringProperty detailsProperty() {
		return this.details;
	}
	

	public final String getDetails() {
		return this.detailsProperty().get();
	}
	

	public final void setDetails(final String details) {
		this.detailsProperty().set(details);
	}
	

	public final ObjectProperty<LocalDate> dueDateProperty() {
		return this.dueDate;
	}
	

	public final LocalDate getDueDate() {
		return this.dueDateProperty().get();
	}
	

	public final void setDueDate(final LocalDate dueDate) {
		this.dueDateProperty().set(dueDate);
	}

	public Todo copy() {
		return new Todo(getId(), getTitle(), getDetails(), getDueDate());
	}
	
	@Override
	public String toString(){
		return "TODO: "+this.getId()+", "+this.getTitle() +", "+this.getDetails()+", "+this.getDueDate();
	}
}
