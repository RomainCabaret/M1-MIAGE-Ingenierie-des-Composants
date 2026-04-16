package ihmconvertisseurdyntr2;

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

		
	private ServiceTrackerPerso tr;

	public void start(BundleContext c) throws Exception {

		tr = new ServiceTrackerPerso(c, Convertisseur.class, null);
		tr.open();
	}

	public void stop(BundleContext c) throws Exception {
		tr.stop();
		tr.close();
	}
	
}
