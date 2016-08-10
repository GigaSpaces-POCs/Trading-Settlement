package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CustomDateFormatter implements CellFormatter{

	private String attribute;
	public CustomDateFormatter() { }
	
	public CustomDateFormatter(String attribute) {
		this.attribute = attribute;
	}
	@Override
	public String format(Object value, ListGridRecord record, int rowNum,
			int colNum) {
		DateTimeFormat fmt = DateTimeFormat.getFormat("MM/dd/yyyy HH:mm:ss SSS");
		return fmt.format(record.getAttributeAsDate(attribute));
		
	}

}
