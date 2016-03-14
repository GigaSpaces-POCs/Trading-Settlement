package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import java.util.Date;

import com.allen_sauer.gwt.log.client.Log;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManager;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManagerAsync;
import com.gigaspaces.settlement.web.blotter.shared.TradeRecord;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TradeViewLayout extends VLayout {
	
	private final DealManagerAsync dealManager = GWT.create(DealManager.class);
	private TradeViewForm tradeViewForm;

	public TradeViewLayout() {

	}

	public Canvas getPanel() {
		HLayout mainLayout = new HLayout(15);
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		HLayout marginLayout = new HLayout();
		marginLayout.setWidth(20);
		
		tradeViewForm = new TradeViewForm();
		
		tradeViewForm.addSaveListener(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Log.info("Saving trade...");
				saveTrade();
			}

		});
		
		mainLayout.addMember(marginLayout);
		mainLayout.addMember(tradeViewForm);
		mainLayout.addMember(marginLayout);

		return mainLayout;
	}

	protected void saveTrade() {
		
		try {
			TradeRecord tradeRecord = new TradeRecord();
			tradeRecord.setTradingParty(tradeViewForm
					.getValueAsString("tradingParty"));
			tradeRecord.setCounterparty(tradeViewForm
					.getValueAsString("counterparty"));
			tradeRecord.setAccount(tradeViewForm.getValueAsString("account"));
			tradeRecord.setAmount(new Double(tradeViewForm.getValueAsString("amount")));
			tradeRecord.setInstrument(tradeViewForm.getValueAsString("instrument"));
			tradeRecord.setBuySellFlag(tradeViewForm.getValueAsString("buySellFlag"));
			tradeRecord.setValueDate((Date) tradeViewForm.getValue("valueDate"));
			tradeRecord.setTradeDate(new Date());
			
			Log.debug("Saving trade: " + tradeRecord);

			try {
				dealManager.saveTrade(tradeRecord,
						new AsyncCallback<TradeRecord>() {

							@Override
							public void onFailure(Throwable t) {
								Log.error("Error saving trade: " + t);
							}

							@Override
							public void onSuccess(TradeRecord trade) {
								Log.info("Successfully Saved Trade: " + trade);
							}
						});
			} catch (Exception e) {
				Log.error("Error saving trade: " + e, e);
			}
		} catch (Exception e) {
			Log.error("Error saving trade: " + e.getMessage(), e);
		}
	}

}
