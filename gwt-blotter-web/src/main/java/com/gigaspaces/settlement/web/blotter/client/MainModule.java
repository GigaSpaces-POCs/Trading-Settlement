package com.gigaspaces.settlement.web.blotter.client;

import com.allen_sauer.gwt.log.client.Log;
import com.gigaspaces.settlement.web.blotter.client.layoutmanager.AdminLayout;
import com.gigaspaces.settlement.web.blotter.client.layoutmanager.BlotterViewLayout;
import com.gigaspaces.settlement.web.blotter.client.layoutmanager.LoginForm;
import com.gigaspaces.settlement.web.blotter.client.layoutmanager.TradeViewLayout;
import com.gigaspaces.settlement.web.blotter.client.manager.LoginManager;
import com.gigaspaces.settlement.web.blotter.client.manager.LoginManagerAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MainModule implements EntryPoint {

	private static final int HEADER_AREA_HEIGHT = 60;

	private VLayout mainLayout;
	private LoginForm loginForm;
	private TabSet topTabSet;
	private BlotterViewLayout blotterViewLayout;

	private final LoginManagerAsync loginManager = GWT.create(LoginManager.class);

	public void onModuleLoad() {
		Log.debug("Loading main module");

		Window.enableScrolling(true);
		Window.setMargin("0px");

		Label header = new Label();
		header.setContents("<h1 id=\"title-heading\" class=\"pagetitle\"> "
				+ "<span id=\"title-text\" style=\"color:#003366;font-size:1.2em;font-family:Arial;font-weight:bold;\">Trading Settlement Demo</span> "
				+ "</h1>");
		header.setAlign(Alignment.CENTER);
		header.setWidth("75%");
		
		final Label loggedInUser = new Label();
		loggedInUser.setPadding(10);
		loggedInUser.setContents("<b>Not Logged In</b>");
		loggedInUser.setAlign(Alignment.LEFT);
		loggedInUser.setValign(VerticalAlignment.CENTER);
		loggedInUser.setHeight(20);
		loggedInUser.setWidth("25%");
		
		Label logoutLink = new Label("<b>Logout</b>");
		logoutLink.addStyleName("clickable");
		logoutLink.setPadding(10);
		logoutLink.setHeight(20);
		logoutLink.setAlign(Alignment.LEFT);
		logoutLink.setValign(VerticalAlignment.CENTER);
		// Set the width to the length of the text.
		logoutLink.setWidth("25%");	


		HLayout headerLayout = new HLayout();
		headerLayout.setHeight(HEADER_AREA_HEIGHT);
		headerLayout.setAlign(Alignment.CENTER);
		headerLayout.setWidth("100%");
		headerLayout.addMember(header);
		headerLayout.addMember(loggedInUser);
		headerLayout.addMember(logoutLink);

		// initialise the main layout container
		mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		final VLayout loginFormLayout = new VLayout();
		loginFormLayout.setHeight(HEADER_AREA_HEIGHT);
		loginFormLayout.setAlign(Alignment.CENTER);
		loginFormLayout.setWidth("100%");

		loginForm = new LoginForm();
		IButton loginButton = new IButton("Login");
		
		Log.debug("Adding login submit listener");
		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					Log.debug("Logging in user");
					login();
					loginFormLayout.hide();
					loggedInUser.setContents("<b>Welcome " + loginForm.getUsername().getValue().toString() + "</b>");

				} catch (Exception e) {
					Log.error("Error in login click handler", e);
				}
			}
		});
		
		loginFormLayout.addMember(loginForm);
		loginFormLayout.addMember(loginButton);
		
		// Logoff click handler
		logoutLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					Log.debug("Logging off user");
					logoff();
					blotterViewLayout.stopTimer();
					topTabSet.hide();
					loginFormLayout.show();
					loggedInUser.setContents("<b>Not Logged In</b>");
				} catch (Exception e) {
					Log.error("Error in login click handler", e);
				}
			}
		});

		Log.debug("Drawing main layout");
		mainLayout.addMember(headerLayout);
		mainLayout.addMember(loginFormLayout);
		mainLayout.draw();

	}

	private void logoff() {
		try {
			loginManager.logoff(new AsyncCallback<Object>() {

				@Override
				public void onFailure(Throwable t) {
					Log.error("Error logging off user", t);
				}

				@Override
				public void onSuccess(Object o) {
					Log.info("User successfully logged off");
				}
				
			});
		} catch (Exception e) {
			Log.error("Error in logoff method", e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void login() {
		try {
			// First try the login
			if (loginForm.getUsername().getValue() == null || loginForm.getPassword().getValue() == null) {
				throw new Exception("Missing Fields");
			}
			loginManager.login(loginForm.getUsername().getValue().toString(),
					loginForm.getPassword().getValue().toString(),
					new AsyncCallback() {

						@Override
						public void onFailure(Throwable t) {
							Log.error("Failed logging in user", t);
						}

						@Override
						public void onSuccess(Object arg0) {
							try {
								Log.info("Successful login");
								Layout paneContainerProperties = new Layout();
								paneContainerProperties.setLayoutMargin(0);
								paneContainerProperties.setLayoutTopMargin(1);

								Log.info("Creating TabSet");
								topTabSet = new TabSet();
								topTabSet.setPaneContainerProperties(paneContainerProperties);
								topTabSet.setTabBarPosition(Side.TOP);
								topTabSet.setWidth100();
								topTabSet.setHeight100();

								Log.info("Creating Trade Blotter Tab");
								Tab blotterViewTab = new Tab("Trade Blotter");
								blotterViewLayout = new BlotterViewLayout();
								blotterViewTab.setPane(blotterViewLayout.getPanel());

								Log.info("Creating New Trade tab");
								Tab addTradeTab = new Tab("New Trade");
								addTradeTab.setPane(new TradeViewLayout().getPanel());

								Tab adminTab = new Tab("Administration");
								adminTab.setPane(new AdminLayout());
								
								topTabSet.addTab(blotterViewTab);
								topTabSet.addTab(addTradeTab);
								topTabSet.addTab(adminTab);

								Log.info("Showing search page");
								// add the Tab layout container to main layout
								// container
								
								mainLayout.addMember(topTabSet);
								mainLayout.draw();

							} catch (Exception e) {
								Log.error("Error executing remote login", e);
							}

						}

					});

		} catch (Exception e) {
			Log.error("Error in login method", e);
		}

	}

}