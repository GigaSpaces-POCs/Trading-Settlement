package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import static com.gigaspaces.settlement.web.blotter.shared.Constants.ACCOUNT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.AMOUNT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.BUY_SELL_FLAG;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.CPTY;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.INSTRUMENT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.REFERENCE;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.TRADE_DATE;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.TRADING_PARTY;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.VALUE_DATE;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class TradeBlotterListGrid extends ListGrid {

	public TradeBlotterListGrid(){
		setWidth100();
		setHeight("25%");
		
		setShowAllRecords(true);
		setBodyOverflow(Overflow.VISIBLE);
		setOverflow(Overflow.VISIBLE);  
		setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);  
		setCanEdit(false);
		setCanDragRecordsOut(false);
		setHoverWidth(200);
		setHoverHeight(20);
		setSelectionType(SelectionStyle.SINGLE);
		setBaseStyle("myOtherGridCellOver");
        
        ListGridField tradingPartyField = new ListGridField(TRADING_PARTY,TRADING_PARTY);
        ListGridField referenceField = new ListGridField(REFERENCE, REFERENCE);
		ListGridField counterpartyField = new ListGridField(CPTY, CPTY);
		ListGridField instrument = new ListGridField(INSTRUMENT, INSTRUMENT);
		ListGridField buySellFlag = new ListGridField(BUY_SELL_FLAG, BUY_SELL_FLAG);
		ListGridField amount = new ListGridField(AMOUNT, AMOUNT);
		amount.setShowGridSummary(true);  
		amount.setCellFormatter(new CellFormatter() { 
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value == null) return null;   
                try {   
                    NumberFormat nf = NumberFormat.getFormat("#,##0.00");   
                    return "" + nf.format(((Number) value).doubleValue());   
                } catch (Exception e) {   
                    return value.toString();   
                }   
 
			}   
        });   
		ListGridField account = new ListGridField(ACCOUNT, ACCOUNT);
		ListGridField tradeDate = new ListGridField(TRADE_DATE, TRADE_DATE);
		ListGridField valueDate = new ListGridField(VALUE_DATE, VALUE_DATE);

		
        setShowGridSummary(false);
        setGroupStartOpen(GroupStartOpen.ALL);

        setFields(tradingPartyField, referenceField, counterpartyField,
				instrument,buySellFlag, amount, account, tradeDate, valueDate);
        setDrawAllMaxCells(2000);
        
	}
}
