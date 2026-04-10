package c2dynec;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import Types_exo1_td1.TypeService2;

public class EcouteurImpl implements ServiceListener {
	
	private ServiceReference<TypeService2> refGet;
	private TypeService2 service;
	private BundleContext contexte;
	
	public EcouteurImpl(BundleContext c) {
		this.contexte = c;
		refGet = c.getServiceReference(TypeService2.class);
		
		if(refGet != null) {
			service = c.getService(refGet);
			System.out.println("C2: un service trouve");
		} else {
			service = null;
			System.out.println("C2: aucun service trouve");
		}
		
		
	}
	

	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> r = event.getServiceReference();

		String[] t = (String[]) r.getProperty("objectClass");
		if (t[0].equals(TypeService2.class.getName())) {
			ServiceReference<TypeService2> re = (ServiceReference<TypeService2>) r;
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
	private void traitementNouveauService(ServiceReference<TypeService2> re) {
		if(refGet != null) {
			service = contexte.getService(refGet);
			System.out.println("C2: un service trouve");
		} else {
			service = null;
			System.out.println("C2: aucun service trouve");
		}
		
	}

	private void traitementModificationService(ServiceReference<TypeService2> re) {	
		// PAS DE PROPRIETE A SURVEILLER
	}

	private void traitementDepartService(ServiceReference<TypeService2> re) {
		if(re.equals(refGet)) {
			contexte.ungetService(re);
			service=null;
			refGet=null;
			
			refGet = contexte.getServiceReference(TypeService2.class);
			if(refGet != null) {
				service = contexte.getService(refGet);
				System.out.println("C2: un service trouve");
			} else {
				service = null;
				System.out.println("C2: aucun service trouve");
			}
			
		}
			
	}
	
	
	public void stop(BundleContext c){
		if(refGet != null) {
			c.ungetService(refGet);
			service = null;
		}
	}

}
