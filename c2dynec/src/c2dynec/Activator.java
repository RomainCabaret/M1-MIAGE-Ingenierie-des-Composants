package c2dynec;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import Types_exo1_td1.TypeService1;
import Types_exo1_td1.TypeService2;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	
	private EcouteurImpl ec;
	private ServiceRegistration<TypeService1> refPost;

	public void start(BundleContext c) throws Exception {		
		ec = new EcouteurImpl(c);
		context.addServiceListener(ec);
		
		refPost = c.registerService(TypeService1.class, new TypeService1Impl(), null);
		System.out.println("Le service C2 est disponible !");

	}

	public void stop(BundleContext c) throws Exception {
		refPost.unregister(); // ORDRE TRES IMPORTANT
		ec.stop(c);
		context.removeServiceListener(ec);

	}
}
