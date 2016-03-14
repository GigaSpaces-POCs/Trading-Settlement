package com.gigaspaces.settlement.util;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.alert.Alert;
import org.openspaces.admin.alert.AlertManager;
import org.openspaces.admin.alert.config.parser.XmlAlertConfigurationParser;
import org.openspaces.admin.alert.events.AlertTriggeredEventListener;

public class AlertHandler {

	public static void main(String[] args) {
		Admin admin = new AdminFactory().createAdmin();

		 AlertManager alertManager = admin.getAlertManager();

		 alertManager.configure(new XmlAlertConfigurationParser("alerts.xml").parse());
		        
		 alertManager.getAlertTriggered().add(new AlertTriggeredEventListener() {

		            public void alertTriggered(Alert alert) {
		                System.out.println(alert);
		            }
		 });
	}
}
