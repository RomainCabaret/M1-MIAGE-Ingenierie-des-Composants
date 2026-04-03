package c2dynec;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import Types_exo1_td1.TypeService1;
import Types_exo1_td1.TypeService2;

public class Activator implements BundleActivator, ServiceListener {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

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
	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> r = event.getServiceReference();

		String[] t = (String[]) r.getProperty("objectClass");
		if (t[0].equals(TypeService1.class.getName())) {
			ServiceReference<TypeService1> re = (ServiceReference<TypeService1>) r;
			switch (event.getType()) {
				case ServiceEvent.UNREGISTERING:
					traitementDepartService(re);
					break;
				case ServiceEvent.REGISTERED:
					traitementNouveauService(re);
					break;	
				case ServiceEvent.MODIFIED:
					traitementModificationService(re);
					break;
			}
		}
	}
	private void traitementNouveauService(ServiceReference<TypeService1> re) {		
	}

	private void traitementModificationService(ServiceReference<TypeService1> re) {		
	}

	private void traitementDepartService(ServiceReference<TypeService1> re) {}
}
