package ihmconvertisseurdyntr2;

import contratconvertisseur.Convertisseur;
import convertisseurframe.ConvertisseurFrame;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ServiceTrackerPerso extends ServiceTracker<Convertisseur, Convertisseur> {
	
	private ConvertisseurFrame frame;
	private ServiceReference<Convertisseur> ref;
	private Convertisseur c;
	private BundleContext context;


	public ServiceTrackerPerso(BundleContext context, Class<Convertisseur> clazz,
			ServiceTrackerCustomizer<Convertisseur, Convertisseur> customizer) {
		super(context, clazz, customizer);
		this.context = context;
		
		frame = new ConvertisseurFrame("Convertisseur") {
			protected void closingOperation() {
				try {
					context.getBundle().stop();

				} catch (BundleException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public Convertisseur addingService(ServiceReference<Convertisseur> re) {
		if(ref == null) {
			ref = re;
			c = context.getService(ref);
			frame.setConvertisseur(c);
			System.out.println("Un service convertisseur trouve");
			
		}
		return c;
	}
	public void removedService(ServiceReference<Convertisseur> re, Convertisseur s) {
		if(re.equals(ref)) {
			context.ungetService(ref);
			c=null;
			ref=null;
			
			ref = context.getServiceReference(Convertisseur.class);
			if(ref !=null) {
				c = context.getService(ref);
			} 
			frame.setConvertisseur(c);
		}
	}
	
	public void modifiedService(ServiceReference<Convertisseur> re, Convertisseur s) {}
	
	public void stop() {
		frame.dispose();
		frame=null;
		
		if(ref==null) {
			context.ungetService(ref);
			ref=null;
			c=null;
		}
		
	}

}
