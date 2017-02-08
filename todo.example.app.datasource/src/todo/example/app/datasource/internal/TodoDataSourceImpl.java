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

	private int findTodoInList(Todo _changedTodo) {
		for (Todo todo : todoList) {
			if(todo.getId() == _changedTodo.getId()){
				return todoList.indexOf(todo);
			}
		}
		return -1;
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

	@Override
	public void update(Todo _changedTodo) {
		TodoService todoService = ejbLookupService.lookup(TodoService.class);

		TodoEntity updateTodoEntity = todoService.find(_changedTodo.getId());
		
		copyElementToEntity(updateTodoEntity, _changedTodo);
		
		TodoEntity _tmpEntity = todoService.update(updateTodoEntity);
		
		int i = findTodoInList(_changedTodo);
		Todo updatedTodoElement = new Todo(_tmpEntity.getId(), _tmpEntity.getTitle(), _tmpEntity.getDetails(), _tmpEntity.getDueDate().toLocalDate());
		
		// Use todoService to save data und refresh UI after success
		Platform.runLater(() -> {
			todoList.set(i, updatedTodoElement);
		});
	}

	@Override
	public void saveNewTodo(Todo newTodoElement) {
		TodoService todoService = ejbLookupService.lookup(TodoService.class);
		Date dueDate = Date.valueOf(newTodoElement.getDueDate());
		TodoEntity newTodoEntity = new TodoEntity(newTodoElement.getTitle(), newTodoElement.getDetails(), dueDate);
		
		TodoEntity _tmpEntity = todoService.saveNew(newTodoEntity);
		
		copyEntityToElement(newTodoElement, _tmpEntity);
		
		// Use todoService to save data und refresh UI after success
		Platform.runLater(() -> {
			System.out.println("(DS) save new");
			todoList.add(newTodoElement);
		});
	}

	private void copyEntityToElement(Todo todoElement, TodoEntity todoEntity) {
		todoElement.setId(todoEntity.getId());
		todoElement.setTitle(todoEntity.getTitle());
		todoElement.setDetails(todoEntity.getDetails());
		todoElement.setDueDate(todoEntity.getDueDate().toLocalDate());
	}
	
	private void copyElementToEntity(TodoEntity todoEntity, Todo changedTodo) {
		todoEntity.setTitle(changedTodo.getTitle());
		todoEntity.setDetails(changedTodo.getDetails());
		todoEntity.setDueDate(Date.valueOf(changedTodo.getDueDate()));
	}
}
