package c1dynec;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import Types_exo1_td1.TypeService1;

public class Activator implements BundleActivator, ServiceListener {

	private TypeService1 a;
	private ServiceReference<TypeService1> ref;
	private BundleContext context;
	private TypeService1 service;

	public void start(BundleContext c) throws Exception {
		this.context = c;
		context.addServiceListener(this);
		ref = c.getServiceReference(TypeService1.class);

		if (ref != null) {
			service = c.getService(ref);
			System.out.println("C1: un service trouvé");

		} else {
			// service = null;

			throw new Exception(); // SI OBLIGATOIRE
		}

		System.out.println("Le service C1 est disponible !");

	}

	public void stop(BundleContext c) throws Exception {
		context.removeServiceListener(this);
		context.ungetService(ref);
		ref = null;
		a=null;
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

	private void traitementDepartService(ServiceReference<TypeService1> re) {
		if (re.equals(ref)) {
			context.ungetService(ref);
			a = null;
			
			ref = context.getServiceReference(TypeService1.class);
			if(ref != null) {
				service = context.getService(ref);
				System.out.println("C1: un autre service trouvé");

			} else {
				// ENVOYER UN EVENEMENT STOP() pour arreter le composant
				try {
					context.getBundle().stop();
					
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}

		}
		
	}

}
