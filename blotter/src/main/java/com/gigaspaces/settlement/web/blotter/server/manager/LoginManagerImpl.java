package com.gigaspaces.settlement.web.blotter.server.manager;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gigaspaces.internal.utils.StringUtils;
import com.gigaspaces.settlement.web.blotter.client.manager.LoginManager;
import com.gigaspaces.settlement.web.blotter.shared.Constants;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginManagerImpl extends RemoteServiceServlet implements LoginManager {

	private static final long serialVersionUID = -607339886153372201L;
	private static final Log LOG = LogFactory.getLog(LoginManagerImpl.class);

	@Override
	public void login(String user, String password) throws Exception {
		// Check to make sure there is one and only one "@"
		if (StringUtils.countOccurrencesOf(user, "@") != 1) {
			throw new Exception("Invalid user name format");
		} else {
			// Remote login  - assume valid for now
			// Add to session
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute(Constants.USER_KEY, user);
		}
	}

	@Override
	public void logoff() throws Exception {
		HttpSession session = getThreadLocalRequest().getSession();
		session.invalidate();
	}

}
