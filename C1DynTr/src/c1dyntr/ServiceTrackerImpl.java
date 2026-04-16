package c1dyntr;

import Types_exo1_td1.TypeService1;

import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ServiceTrackerImpl implements ServiceTrackerCustomizer<TypeService1, TypeService1>{

	@Override
	public TypeService1 addingService(ServiceReference<TypeService1> arg0) {
		Activator.getContext();
		return null;
	}

	@Override
	public void modifiedService(ServiceReference<TypeService1> arg0, TypeService1 arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removedService(ServiceReference<TypeService1> arg0, TypeService1 arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
