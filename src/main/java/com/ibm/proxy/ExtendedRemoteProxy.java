package com.ibm.proxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.GridRegistry;
import org.openqa.grid.internal.TestSession;
import org.openqa.grid.internal.listeners.TestSessionListener;
import org.openqa.grid.selenium.proxy.DefaultRemoteProxy;

import com.ibm.util.ScreenRecorderUtil;

public class ExtendedRemoteProxy extends DefaultRemoteProxy implements TestSessionListener{

	private static String RECORD_VIDEO = "recordVideo";
	
	public ExtendedRemoteProxy(RegistrationRequest registrationRequest, GridRegistry gridRegistry) {
		super(registrationRequest, gridRegistry);
		// TODO Auto-generated constructor stub
		System.out.println("Registry Key    : "+GridRegistry.KEY);
		System.out.println("Registry        : "+gridRegistry.getHub().getConfiguration().registry);
		System.out.println("Hub URL         : "+gridRegistry.getHub().getUrl());
		System.out.println("Hub Console URL : "+gridRegistry.getHub().getConsoleURL());
		/*Set<TestSession> activeSessionSet = gridRegistry.getActiveSessions();
		activeSessionSet.forEach(System.out::println);
		ProxySet allProxies = gridRegistry.getAllProxies();
		allProxies.forEach(System.out::println);*/
	}
	
	@Override
	public void beforeCommand(TestSession session, HttpServletRequest request, HttpServletResponse response) {
		//session.put("lastCommand", request.getMethod() + " - " + request.getPathInfo() + " executing ...");
		System.out.println("lastCommand "+ request.getMethod() + " - " + request.getPathInfo() + " executing ...");
		//System.out.println("Session                  : "+session.toString());
		System.out.println("Request URL              : "+request.getRequestURL());
		//System.out.println("Request Header Names     : "+request.getHeaderNames());
	}
	
	@Override
	public void afterCommand(TestSession session, HttpServletRequest request, HttpServletResponse response) {
		//session.put("lastCommand", request.getMethod() + " - " + request.getPathInfo() + " executed.");
		System.out.println("lastCommand "+ request.getMethod() + " - " + request.getPathInfo() + " executed.");
		//System.out.println("Session                  : "+session.toString());
		System.out.println("Response Status          : "+response.getStatus());
		//System.out.println("Response Header Names    : "+response.getHeaderNames());
	}
	
	@Override
	public void beforeSession(TestSession session) {
		System.out.println("Extending Selenium Grid - Before Session");
		System.out.println("Session : "+session.toString());
		boolean record = (Boolean)session.getRequestedCapabilities().get(RECORD_VIDEO);
	    if (record) {
	    	try {
	    		ScreenRecorderUtil.startRecord("MyVideo");
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	        System.out.println("Video Recording value is "+record);
	    }
	    
	    System.out.println("Active Sessions in Registry");
		session.getSlot().getProxy().getRegistry().getActiveSessions().forEach(System.out::println);
		System.out.println("All Proxies in Registry");
		session.getSlot().getProxy().getRegistry().getAllProxies().forEach(System.out::println);
	}
	
	@Override
	public void afterSession(TestSession session) {
		System.out.println("Extending Selenium Grid - After Session");
		System.out.println("Session : "+session.toString());
		try {
    		ScreenRecorderUtil.startRecord("MyVideo");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		
		System.out.println("Active Sessions in Registry");
		session.getSlot().getProxy().getRegistry().getActiveSessions().forEach(System.out::println);
		System.out.println("All Proxies in Registry");
		session.getSlot().getProxy().getRegistry().getAllProxies().forEach(System.out::println);
	}

}
