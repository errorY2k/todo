
package todo.example.app.persistence.service;

import java.util.List;

import javax.ejb.Remote;

import todo.example.app.persistence.model.TodoEntity;

@Remote
public interface TodoService {

//	void update(TodoEntity updateTodo, TodoEntity changedTodo);

	TodoEntity saveNew(TodoEntity newTodo);
	
	void delete(TodoEntity todo);
	
	public List<TodoEntity> getAllTodos();
	
	public TodoEntity find(Object id);

	TodoEntity update(TodoEntity updateTodo);
}
