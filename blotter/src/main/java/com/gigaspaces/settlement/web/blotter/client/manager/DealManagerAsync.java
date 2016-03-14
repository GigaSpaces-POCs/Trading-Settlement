package com.gigaspaces.settlement.web.blotter.client.manager;

import java.util.List;

import com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord;
import com.gigaspaces.settlement.web.blotter.shared.TradeRecord;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DealManagerAsync {

	void getMatchedDeals(String customerName,
			AsyncCallback<List<MatchedDealRecord>> callback) throws Exception;

	void getTrades(String entity, AsyncCallback<List<TradeRecord>> asyncCallback)
			throws Exception;

	void getCounterpartyTrades(String entity,
			AsyncCallback<List<TradeRecord>> asyncCallback) throws Exception;

	void saveTrade(TradeRecord tradeRecord,
			AsyncCallback<TradeRecord> asyncCallback) throws Exception;

	void startFeeder(int refreshInterval, AsyncCallback callback) throws Exception;
	
	void stopFeeder(AsyncCallback callback) throws Exception;

	void clearUnmatchedTrades(AsyncCallback asyncCallback) throws Exception;

}
