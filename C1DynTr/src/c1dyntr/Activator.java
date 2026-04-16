package c1dyntr;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import Types_exo1_td1.TypeService1;

public class Activator implements BundleActivator {
	
	private ServiceTracker<TypeService1, TypeService1> tr;
	private ServiceTrackerImpl trp;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		trp = new ServiceTrackerImpl();
		tr = new ServiceTracker<>(context, TypeService1.class, trp);
		tr.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		tr.close();
	}

}
