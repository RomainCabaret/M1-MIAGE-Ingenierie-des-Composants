package ihmconvertisseurdyncectr;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class Activator implements BundleActivator {

	private ConvertisseurFrame frame;

	private ServiceReference<Convertisseur> sr;
	private Convertisseur service;
	
	private BundleContext contexte;
	
	private ServiceTracker<Convertisseur, Convertisseur> tr;

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
		tr = new ServiceTracker<Convertisseur, Convertisseur>(contexte, Convertisseur.class, null) {
			public Convertisseur addingService(ServiceReference<Convertisseur> re) {
				if(sr == null) {
					sr = re;
					service = contexte.getService(sr);
					frame.setConvertisseur(service);
					System.out.println("Un service convertisseur trouve");
					
				}
				return service;
			}
			public void removedService(ServiceReference<Convertisseur> re, Convertisseur s) {
				if(re.equals(sr)) {
					contexte.ungetService(sr);
					service=null;
					sr=null;
					
					sr = contexte.getServiceReference(Convertisseur.class);
					if(sr != null) {
						service = contexte.getService(sr);
					} 
					frame.setConvertisseur(service);
				}
			}
			
			public void modifiedService(ServiceReference<Convertisseur> re, Convertisseur s) {}
		};
		tr.open();
	}

	public void stop(BundleContext c) throws Exception {
		tr.close();
		frame.dispose();
		frame = null;
		
		if(sr != null) {
			c.ungetService(sr);
			service = null;
			sr = null;
		}

	}
	
}
