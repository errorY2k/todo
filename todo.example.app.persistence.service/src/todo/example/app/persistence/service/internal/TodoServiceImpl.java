package todo.example.app.persistence.service.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import todo.example.app.persistence.model.TodoEntity;
import todo.example.app.persistence.service.TodoService;

@Stateless(mappedName="st1")
public class TodoServiceImpl implements TodoService{

	@PersistenceContext//(name = "todo.example.persistence.service")
	private EntityManager em;
	
	@Override
	public TodoEntity update(TodoEntity updateTodo) {
//		throw new RuntimeException("fehler beim speichern");
		return em.merge(updateTodo);
	}

	@Override
	public TodoEntity saveNew(TodoEntity newTodo) {

		return em.merge(newTodo);
	}

	@Override
	public void delete(TodoEntity todo) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
		em.remove(em.contains(todo) ? todo : em.merge(todo));
	}

	@Override
	public List<TodoEntity> getAllTodos(){
		TypedQuery<TodoEntity> q = em.createQuery("SELECT t FROM TodoEntity t", TodoEntity.class);
		List<TodoEntity> todos = q.getResultList();
		
		return todos;
	}

	@Override
	public TodoEntity find(Object id) {
		return em.find(TodoEntity.class, id);
		
	}
}
