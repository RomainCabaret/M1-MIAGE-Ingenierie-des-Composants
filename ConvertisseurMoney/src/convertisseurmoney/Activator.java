package convertisseurmoney;

import contratconvertisseur.Convertisseur;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	
	private ServiceRegistration<Convertisseur> sr;

	public void start(BundleContext c) throws Exception {
		sr = c.registerService(Convertisseur.class, new ConvertisseurDoEuImpl(), null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		sr.unregister();
	}
	

}
