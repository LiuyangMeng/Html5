package com.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener,HttpSessionAttributeListener {
	
	private long onlinecount=0;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		onlinecount++;
		System.out.println("session + 1:"+onlinecount);
		updatecount(arg0);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		onlinecount--;
		if(onlinecount<0){
			onlinecount=0;
		}
		System.out.println("session - 1:"+onlinecount);
		updatecount(arg0);
		
	}
	
	private void updatecount(HttpSessionEvent hse){
		System.out.println("update:"+onlinecount);
		hse.getSession().getServletContext().setAttribute("onlinecount", onlinecount);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("session replace");
	}

}
