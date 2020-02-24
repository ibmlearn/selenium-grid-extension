package com.ibm.capability.matcher;

import java.util.Map;

import org.openqa.grid.internal.utils.DefaultCapabilityMatcher;

public class ExtendedCapabilityMatcher extends DefaultCapabilityMatcher{
	
	private static final String nodeOS = "nodeOS";
	boolean allCapabilitiesAvailability = false;
	
	@Override
	public boolean matches(Map<String, Object> nodeCapability, Map<String,Object> requestedCapability) {
		boolean defaultCapabilitiesAvailability = super.matches(nodeCapability, requestedCapability);
		/*System.out.println(nodeCapability.entrySet());
		System.out.println(requestedCapability.entrySet());
		System.out.println("defaultCapabilitiesAvailability : "+defaultCapabilitiesAvailability);*/
		if(! requestedCapability.containsKey("nodeOS")) {
			return defaultCapabilitiesAvailability;
		}else {
			
			boolean nodeOSAvailability = nodeCapability.get(nodeOS).equals(requestedCapability.get(nodeOS));
			/*System.out.println("nodeOSAvailability : "+nodeOSAvailability);*/
			allCapabilitiesAvailability = ( defaultCapabilitiesAvailability && nodeOSAvailability);
			return allCapabilitiesAvailability;
		}
	}
	
}
