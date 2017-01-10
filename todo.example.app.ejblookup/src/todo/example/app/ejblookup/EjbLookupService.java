package todo.example.app.ejblookup;

public interface EjbLookupService {

	<T> T lookup(Class<T> class1);

}
