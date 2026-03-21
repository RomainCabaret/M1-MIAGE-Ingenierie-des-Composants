package c2;

import Types_exo1_td1.TypeService1;
import Types_exo1_td1.TypeService2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	
	private ServiceReference<TypeService2> refGet;
	private ServiceRegistration<TypeService1> refPost;
	
	private TypeService2 service;


	public void start(BundleContext c) throws Exception {
		// ------ GET 
		
		refGet = c.getServiceReference(TypeService2.class);
		
		if(refGet != null) {
			service = c.getService(refGet);
		} else {
			service = null;
		}
		
		
		// ------ POST
		
		refPost = c.registerService(TypeService1.class, new TypeService1Impl(), null);
		
		System.out.println("Le service C2 est disponible !");

	}

	public void stop(BundleContext c) throws Exception {
		if(refGet != null) {
			c.ungetService(refGet);
			service = null;
		}
	}

}
