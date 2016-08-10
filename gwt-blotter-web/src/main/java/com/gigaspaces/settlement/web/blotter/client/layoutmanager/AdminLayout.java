package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import com.allen_sauer.gwt.log.client.Log;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManager;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManagerAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AdminLayout extends HLayout {
	private final DealManagerAsync dealManager = GWT.create(DealManager.class);
	
	public AdminLayout() {
        
        final DynamicForm form = new DynamicForm();  
        form.setSize("178px", "11px");
          
        final TextItem refreshIntervalTextItem = new TextItem();
        refreshIntervalTextItem.setTop(0);
        refreshIntervalTextItem.setWrapTitle(false);
        refreshIntervalTextItem.setTitle("Refresh Interval (seconds)");  
        refreshIntervalTextItem.setWidth(50);
        refreshIntervalTextItem.setRequired(true); 
        refreshIntervalTextItem.setDefaultValue("5000");  
		
		form.setFields(refreshIntervalTextItem);
		addMember(form);
		
		VLayout layout = new VLayout();
		layout.setMargin(3);
		layout.setMembersMargin(10);
		IButton startFeederButton = new IButton("Start Feeder");
		startFeederButton.setMargin((Integer) null);
		layout.addMember(startFeederButton);
		
		startFeederButton.addClickHandler(new ClickHandler() {  
		    @SuppressWarnings("rawtypes")
			public void onClick(ClickEvent event) {  
		    	try {
					dealManager.startFeeder(Integer.parseInt(refreshIntervalTextItem.getValueAsString()), 
							new AsyncCallback() {
								@Override
								public void onFailure(Throwable t) {
									Log.error("Error starting feeder", t);
									SC.say("Error starting feeder: " + t.getMessage());
								}

								@Override
								public void onSuccess(Object obj) {
									Log.info("Successfully started Feeder");
									SC.say("Feeder Started Successfully");
								}
					}
					);
				} catch (Exception e) {
					Log.error("Error in onClick of start feeder", e);
				}
		    }  
		});  
		
		IButton stopFeederButton = new IButton("Stop Feeder");
		stopFeederButton.setLeft(260);
		layout.addMember(stopFeederButton);
		
		stopFeederButton.addClickHandler(new ClickHandler() {  
		    @SuppressWarnings("rawtypes")
			public void onClick(ClickEvent event) {  
		    	try {
					dealManager.stopFeeder( 
							new AsyncCallback() {
								@Override
								public void onFailure(Throwable t) {
									Log.error("Error stopping feeder", t);
									SC.say("Error stopping feeder: " + t.getMessage());
								}

								@Override
								public void onSuccess(Object obj) {
									Log.info("Successfully stopped Feeder");
									SC.say("Feeder Stopped Successfully");
								}
					}
					);
				} catch (Exception e) {
					Log.error("Error in onClick of stop feeder", e);
				}
		    }  
		});  
		
		IButton btnClearTrades = new IButton("Clear Trades");
		layout.addMember(btnClearTrades);
		
		btnClearTrades.addClickHandler(new ClickHandler() {  
		    @SuppressWarnings("rawtypes")
			public void onClick(ClickEvent event) {  
		    	try {
					dealManager.clearUnmatchedTrades ( 
							new AsyncCallback() {
								@Override
								public void onFailure(Throwable t) {
									Log.error("Error clearing unmatched trades", t);
									SC.say("Error Clearing Trades: " + t.getMessage());
								}

								@Override
								public void onSuccess(Object obj) {
									Log.info("Successfully cleared unmatched trades");
									SC.say("Trades Cleared Successfully");
								}
					}
					);
				} catch (Exception e) {
					Log.error("Error in onClick of stop feeder", e);
				}
		    }  
		});  
		
		
		addMember(layout);
		layout.moveTo(207, 10);
	}

}
