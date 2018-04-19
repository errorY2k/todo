package test.internal;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import test.ITestOSGI;

@Component
public class TestOSGI implements ITestOSGI{

	@Activate
	void activate() {
		System.err.println("TEST ACTIVATE");
	}
}
