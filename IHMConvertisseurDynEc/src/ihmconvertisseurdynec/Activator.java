package ihmconvertisseurdynec;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class Activator implements BundleActivator, ServiceListener {

	private ConvertisseurFrame frame;

	private ServiceReference<Convertisseur> sr;
	private Convertisseur service;
	
	private BundleContext contexte;

	public void start(BundleContext c) throws Exception {
		this.contexte = c;

		frame = new ConvertisseurFrame("Convertisseur") {
			protected void closingOperation() {
				try {
					c.getBundle().stop();

				} catch (BundleException e) {
					e.printStackTrace();
				}
			}
		};

		sr = c.getServiceReference(Convertisseur.class);
		//PLUS TARD -> c.getAllServiceReferences(null, null) OU getServiceReferences POUR PLUSIEURS SERVICES

		if (sr != null) {
			service = c.getService(sr);
			frame.setConvertisseur(service);
		}
		
		c.addServiceListener(this);

	}

	public void stop(BundleContext c) throws Exception {
		c.removeServiceListener(this);
		frame.dispose();
		frame = null;
		
		if(sr != null) {
			c.ungetService(sr);
			service = null;
			sr = null;
		}

	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> r = event.getServiceReference();

		String[] t = (String[]) r.getProperty("objectClass");
		if (t[0].equals(Convertisseur.class.getName())) {
			ServiceReference<Convertisseur> re = (ServiceReference<Convertisseur>) r;
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
	

	private void traitementNouveauService(ServiceReference<Convertisseur> re) {
		if(sr == null) {
			sr = re;
			service = contexte.getService(sr);
			frame.setConvertisseur(service);
			System.out.println("C2 un sreivex convertisseur trouve");
			
		}
		
	}


	private void traitementDepartService(ServiceReference<Convertisseur> re) {
		if(re.equals(sr)) {
			contexte.ungetService(re);
			service=null;
			sr=null;
			
			sr = contexte.getServiceReference(Convertisseur.class);
			if(sr != null) {
				service = contexte.getService(sr);
			} 
			frame.setConvertisseur(service);
		}
			
	}
	private void traitementModificationService(ServiceReference<Convertisseur> re) {	
		// PAS DE PROPRIETE A SURVEILLER
	}
	

}
