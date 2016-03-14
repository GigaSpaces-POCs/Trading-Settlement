package com.gigaspaces.settlement.web.blotter.client.manager;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loginManager")
public interface LoginManager extends RemoteService {
	void login(String user, String password) throws Exception;

	void logoff() throws Exception;
}
