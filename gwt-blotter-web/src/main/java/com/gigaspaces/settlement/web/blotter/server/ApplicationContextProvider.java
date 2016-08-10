package com.gigaspaces.settlement.web.blotter.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context = null;
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ApplicationContextProvider.context = context;
	}
	
	public static ApplicationContext  getContext() {
		return context;
	}

}
