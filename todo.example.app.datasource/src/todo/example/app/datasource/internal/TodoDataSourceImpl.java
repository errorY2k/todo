package todo.example.app.datasource.internal;

import java.sql.Date;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import todo.example.app.datasource.TodoDataSource;
import todo.example.app.ejblookup.EjbLookupService;
import todo.example.app.model.Todo;
import todo.example.app.persistence.model.TodoEntity;
import todo.example.app.persistence.service.TodoService;

@Component
public class TodoDataSourceImpl implements TodoDataSource {
	
	private ObservableList<Todo> todoList = FXCollections.observableArrayList();

	private EjbLookupService ejbLookupService;

	@Activate
	void activate() {
		System.out.println("ACTIVATE SERVICE");
		initTestData();
	}
	
	@Reference
	void bindEjbLookupService(EjbLookupService ejbLookupService) {
		this.ejbLookupService = ejbLookupService;
	}
	void unbindEjbLookupService(EjbLookupService ejbLookupService) {
		this.ejbLookupService = null;
	}
	
	private void initTestData() {
		TodoService todoService = ejbLookupService.lookup(TodoService.class);
		List<TodoEntity> l = todoService.getAllTodos();
		for (TodoEntity todo : l) {
			Todo item = new Todo();
			item.setId(todo.getId());
			item.setTitle(todo.getTitle());
			item.setDetails(todo.getDetails());
			item.setDueDate(todo.getDueDate().toLocalDate());
			todoList.add(item);
		}
	}
	
	@Override
	public ObservableList<Todo> getTodoList(){
		return todoList;
	}

	@Override
	public void update(Todo _changedTodo) {

		TodoService todoService = ejbLookupService.lookup(TodoService.class);
		TodoEntity updateTodo = todoService.find(_changedTodo.getId());

		updateTodoEntity(updateTodo, _changedTodo);
		
		TodoEntity _tmp = todoService.update(updateTodo);
		
		int i = findTodoInList(_changedTodo);
		Todo newTodo = new Todo(_tmp.getId(), _tmp.getTitle(), _tmp.getDetails(), _tmp.getDueDate().toLocalDate());
		
		// Use todoService to save data und refresh UI after success
		Platform.runLater(() -> {
			todoList.set(i, newTodo);
		});
	}

	private int findTodoInList(Todo _changedTodo) {
		for (Todo todo : todoList) {
			if(todo.getId() == _changedTodo.getId()){
				return todoList.indexOf(todo);
			}
		}
		return -1;
	}

	@Override
	public void saveNewTodo(Todo todo) {
		TodoService todoService = ejbLookupService.lookup(TodoService.class);
		Date dueDate = Date.valueOf(todo.getDueDate());
		TodoEntity newTodo = new TodoEntity(todo.getTitle(), todo.getDetails(), dueDate);
		
		TodoEntity _tmp = todoService.saveNew(newTodo);
		
		updateTodo(todo, _tmp);
		
		// Use todoService to save data und refresh UI after success
		Platform.runLater(() -> {
			System.out.println("(DS) save new");
			todoList.add(todo);
		});
	}
	
	@Override
	public void deleteTodo(Todo _todo) {
		TodoService todoService = ejbLookupService.lookup(TodoService.class);
		TodoEntity todo = todoService.find(_todo.getId());
		todoService.delete(todo);
		
		int i = findTodoInList(_todo);
		Platform.runLater(() -> {
			todoList.remove(i);
		});
	}

	private void updateTodo(Todo updateTodo, TodoEntity changeTodo) {
		updateTodo.setId(changeTodo.getId());
		updateTodo.setTitle(changeTodo.getTitle());
		updateTodo.setDetails(changeTodo.getDetails());
		updateTodo.setDueDate(changeTodo.getDueDate().toLocalDate());
	}
	
	private void updateTodoEntity(TodoEntity updateTodo, Todo changedTodo) {
		updateTodo.setTitle(changedTodo.getTitle());
		updateTodo.setDetails(changedTodo.getDetails());
		updateTodo.setDueDate(Date.valueOf(changedTodo.getDueDate()));
	}
}
