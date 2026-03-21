package convertisseurtemp;

import contratconvertisseur.Convertisseur;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator, Convertisseur {
	
	private ServiceRegistration<Convertisseur> sr;
	

	
	public void start(BundleContext c) throws Exception {
		sr = c.registerService(Convertisseur.class, this, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		sr.unregister();
	}

	@Override
	public String fromUnit() {
		return "Fahrenheit";
	}

	@Override
	public String toUnit() {
		return "Celsius";
	}

	@Override
	public double u12u2(double f) {
		return (f-32)*5/9;
	}

}
