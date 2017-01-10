package todo.example.app.ejblookup.internal;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import todo.example.app.ejblookup.EjbLookupService;
import todo.example.app.persistence.service.TodoService;
import todo.example.app.persistence.service.internal.TodoServiceImpl;

@Component
public class EjbLookupServiceImpl implements EjbLookupService{

	String appName = "";
	String moduleName = "todo.example.app.persistence.service";
	String distinctName = "";
	String beanName = TodoServiceImpl.class.getSimpleName();
	String interfaceName = TodoService.class.getName();
	
	InitialContext context = null;
	Properties props = new Properties();
	
	@Activate
	void activate() {
		props.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		props.put("java.naming.provider.url", "http-remoting://127.0.0.1:8080");
		props.put("jboss.naming.client.ejb.context", "true");
		try {
			context = new InitialContext(props);
		} catch (NamingException e) {
			throw new RuntimeException("Activate", e);
		}
		System.out.println("ACTIVATE LOOKUP");
	}
	
	@Override
	public <T> T lookup(Class<T> clazz) {
		String name = "java:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!"
				+ interfaceName;
		T service = null;
		try {
			service = (T) context.lookup(name);
		} catch (NamingException e) {
			throw new RuntimeException("Lookup", e);
		}
		return (T) service;
	}
}
