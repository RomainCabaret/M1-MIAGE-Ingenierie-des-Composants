package c3;

import Types_exo1_td1.TypeService1;
import Types_exo1_td1.TypeService2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ActivateurC3 implements BundleActivator, TypeService2 {
	
	private ServiceRegistration<TypeService2> sr;

	public void start(BundleContext bundleContext) throws Exception {
		sr = bundleContext.registerService(TypeService2.class, new TypeService2Impl(), null);
		System.out.println("Le service C3 est disponible !");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		if(sr != null) {
			sr.unregister();
			sr = null;
		}
		System.out.println("Le service C3 n'est plus disponible !");
		
	}

}
