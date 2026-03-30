package ihmobligatoire;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

public class Activator implements BundleActivator {

	private ConvertisseurFrame frame;

	private ServiceReference<Convertisseur> sr;
	private Convertisseur service;

	public void start(BundleContext c) throws Exception {
		
		sr = c.getServiceReference(Convertisseur.class);

		if (sr == null) {
			throw new Exception();
		}


		frame = new ConvertisseurFrame("Convertisseur") {
			protected void closingOperation() {
				try {
					c.getBundle().stop();

				} catch (BundleException e) {
					e.printStackTrace();
				}
			}
		};

		
		
		service = c.getService(sr);
		frame.setConvertisseur(service);
	}

	public void stop(BundleContext c) throws Exception {
		frame.dispose();
		frame = null;
		
		if(sr != null) {
			c.ungetService(sr);
			service = null;
			sr = null;
		}

	}

}
