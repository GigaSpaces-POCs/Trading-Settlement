package com.gigaspaces.settlement.web.blotter.client.manager;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginManagerAsync {
	@SuppressWarnings("rawtypes")
	void login(String user, String password, AsyncCallback callback) throws Exception;
	
	@SuppressWarnings("rawtypes")
	void logoff(AsyncCallback callback) throws Exception;
}
