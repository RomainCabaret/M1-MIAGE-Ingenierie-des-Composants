package c1;

import Types_exo1_td1.TypeService1;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	
	private ServiceReference<TypeService1> sr;
	private	TypeService1 service;


	public void start(BundleContext c) throws Exception {
		sr = c.getServiceReference(TypeService1.class);
		
		if(sr != null) {
			service = c.getService(sr);
					
		} else {
			//service = null;
			
			throw new Exception(); // SI OBLIGATOIRE 
		}
		
		System.out.println("Le service C1 est disponible !");

		
	}

	public void stop(BundleContext c) throws Exception {
		if(sr != null) {
			c.ungetService(sr);
			service = null;
		}
	}

}
