package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManager;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManagerAsync;
import com.gigaspaces.settlement.web.blotter.shared.ListGridMatchedDealRecord;
import com.gigaspaces.settlement.web.blotter.shared.ListGridTradeRecord;
import com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord;
import com.gigaspaces.settlement.web.blotter.shared.TradeRecord;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class BlotterViewLayout extends VLayout {
	
	private VLayout gridsLayout;

	private SearchForm searchForm;
	private TradeBlotterListGrid ourTradesListGrid;
	private TradeBlotterListGrid cptyTradesListGrid;
	private MatchedDealBlotterListGrid completedDealsListGrid;
	private Timer timer;
	
	private int numberOfClicks = 0;

	private int repeatInterval = 5000;

	private final DealManagerAsync dealManager = GWT.create(DealManager.class);


	public Canvas getPanel() {
		Window.enableScrolling(true);
		Window.setMargin("0px");

		SectionStack gridsSessionStack = new SectionStack();
		gridsSessionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		gridsSessionStack.setWidth100();
//		gridsSessionStack.setHeight("75%");

		HLayout mainLayout = new HLayout(15);
		HLayout marginLayout = new HLayout(20);

		gridsLayout = new VLayout(15);
		gridsLayout.setWidth100();
		
		HLayout formLayout = new HLayout();
		formLayout.setSize("100%", "41px");
		searchForm = new SearchForm();
		searchForm.setHeight("32px");
		
		IButton searchButton = new IButton("Search");
		searchButton.setLeft(200);
		searchButton.setIcon("find.png");
		
		searchButton.addClickHandler(new ClickHandler() {
      
			@Override
			public void onClick(ClickEvent event) {
				if (numberOfClicks++ > 20) {
					
				}
				timer = new Timer() {

					@Override
					public void run() {
						processSearchRequest();
					}

				};

				// Schedule the timer to run once in 5 seconds.
				timer.scheduleRepeating(repeatInterval);
			}

		});
		
//		DynamicForm refreshIntervalForm = new DynamicForm();
//		refreshIntervalForm.setHeight("32px");
//		SelectItem refreshIntervalDropDown = new SelectItem("refreshInterval", "Refresh Interval");
//		refreshIntervalDropDown.setValueMap("5","10","15","20");
//		refreshIntervalDropDown.addChangeHandler(new ChangeHandler() {
//			@Override
//			public void onChange(ChangeEvent event) {
//				repeatInterval = (Integer) event.getValue();
//				Log.info("Resetting refresh interval to " + repeatInterval);
//				timer.cancel();
//				timer.scheduleRepeating(repeatInterval * 1000);
//				Log.info("Refresh interval successfully reset");
//			}
//		});
//		refreshIntervalForm.setItems(refreshIntervalDropDown);
//		refreshIntervalForm.draw();
		
		formLayout.addMember(searchForm);
		formLayout.addMember(searchButton);
//		formLayout.addMember(refreshIntervalForm);
		
		ourTradesListGrid = new TradeBlotterListGrid();
		cptyTradesListGrid = new TradeBlotterListGrid();
		completedDealsListGrid = new MatchedDealBlotterListGrid();

		gridsLayout.addMember(formLayout);
		
		String ourDeals = Canvas.imgHTML("user_suit.png") + " Our Trades";
		SectionStackSection ourDealSection = new SectionStackSection(ourDeals);
		ourDealSection.setExpanded(true);
		ourDealSection.setCanCollapse(true);
		ourDealSection.addItem(ourTradesListGrid);

		String cptyDeals = Canvas.imgHTML("user_suit.png")
				+ " CouterParty Trades";
		SectionStackSection cptyDealSection = new SectionStackSection(cptyDeals);
		cptyDealSection.setExpanded(true);
		cptyDealSection.setCanCollapse(true);
		cptyDealSection.setItems(cptyTradesListGrid);

		String completedDeals = Canvas.imgHTML("user_suit.png")
				+ " Matched Deals";
		SectionStackSection completedDealSection = new SectionStackSection(
				completedDeals);
		completedDealSection.setExpanded(true);
		completedDealSection.setCanCollapse(true);
		completedDealSection.setItems(completedDealsListGrid);

//		gridsSessionStack.setSections(tradeViewSection);
		gridsSessionStack.setSections(ourDealSection);
		gridsSessionStack.setSections(cptyDealSection);
		gridsSessionStack.setSections(completedDealSection);
		
		// Start with the trade view hidden
//		gridsSessionStack.hideSection(0);
		
		gridsLayout.addMember(gridsSessionStack);
		// gridsLayout.addMember(cptyDealSectionStack);
		// gridsLayout.addMember(completedDealSectionStack);

		mainLayout.addMember(marginLayout);
		mainLayout.addMember(gridsLayout);
		mainLayout.addMember(marginLayout);

		return mainLayout;
	}
	
	public void stopTimer() {
		timer.cancel();
	}
	
	public void setRepeatInterval(int seconds) {
		this.repeatInterval  = seconds * 1000;
	}

	protected void processSearchRequest() {
		try {
			ourTradesListGrid.clear();
			cptyTradesListGrid.clear();
			completedDealsListGrid.clear();
			
			String counterparty = null;
			Log.debug("Checking if counterparty is set");
			if (searchForm.getCounterpartyTextItem().getValue() != null) {
				Log.debug("Cpty is specified");
				counterparty = searchForm.getCounterpartyTextItem().getValue().toString();
			}
			Log.debug("Cpty value is " + counterparty);

			Log.debug("Searching For Our Trades: ");
			dealManager.getTrades(counterparty, new AsyncCallback<List<TradeRecord>>() {

				@Override
				public void onFailure(Throwable t) {
					Log.debug("Error searching trades", t);
				}

				@Override
				public void onSuccess(List<TradeRecord> results) {
					// Set DealTrade results
					List<ListGridRecord> records = new ArrayList<ListGridRecord>(
							results.size());
					try {
						for (TradeRecord tradeRecord : results) {
							ListGridRecord record = new ListGridTradeRecord(
									tradeRecord);
							records.add(record);
						}

						Log.debug("Setting " + records.size()
								+ " trade records");
						ourTradesListGrid.setData((ListGridRecord[]) records
								.toArray(new ListGridTradeRecord[results.size()]));
					} catch (Throwable e) {
						e.printStackTrace();
						Log.error("Error creating list grid", e);
					}

					Log.debug("Succes - getTrades: " + results.size());

				}

			});
			ourTradesListGrid.draw();

			Log.debug("Searching For Cpty Trades: ");
			dealManager.getCounterpartyTrades(counterparty,
					new AsyncCallback<List<TradeRecord>>() {

						@Override
						public void onFailure(Throwable t) {
							Log.debug("Error searching cpty trades", t);
						}

						@Override
						public void onSuccess(List<TradeRecord> results) {
							// Set DealTrade results
							List<ListGridRecord> records = new ArrayList<ListGridRecord>(
									results.size());
							try {
								for (TradeRecord tradeRecord : results) {
									ListGridRecord record = new ListGridTradeRecord(
											tradeRecord);
									records.add(record);
								}

								Log.debug("Setting " + records.size()
										+ " trade records");
								cptyTradesListGrid.setData((ListGridRecord[]) records
										.toArray(new ListGridTradeRecord[results
												.size()]));
							} catch (Throwable e) {
								e.printStackTrace();
								Log.error("Error creating list grid", e);
							}

							Log.debug("Succes - getCounterpartyTrades: "
									+ results.size());

						}

					});
			cptyTradesListGrid.draw();

			Log.debug("Searching Matched Deals: ");
			dealManager.getMatchedDeals(counterparty,
					new AsyncCallback<List<MatchedDealRecord>>() {

						@Override
						public void onFailure(Throwable t) {
							Log.debug("Error searching trades", t);
						}

						@Override
						public void onSuccess(List<MatchedDealRecord> results) {
							// Set DealRecord results
							if (results != null && results.size() > 0) {
								List<ListGridRecord> records = new ArrayList<ListGridRecord>(
										results.size());
								for (MatchedDealRecord dealRecord : results) {
									ListGridRecord record = new ListGridMatchedDealRecord(dealRecord);
									records.add(record);
								}

								Log.debug("Setting " + records.size()
										+ " trade records");
								completedDealsListGrid.setData((ListGridRecord[]) records
										.toArray(new ListGridMatchedDealRecord[results
												.size()]));

							} else {
								Log.debug("No completed deals");
							}

							Log.debug("Succes - getMatchedDeals: "
									+ results.size());
						}

					});
			completedDealsListGrid.draw();
		} catch (Throwable e) {
			Log.debug("Failed onClick", e);
		}
		// TODO Auto-generated method stub

	}
}
