package com.gigaspaces.settlement.web.blotter.client.manager;

import java.util.List;

import com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord;
import com.gigaspaces.settlement.web.blotter.shared.TradeRecord;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dealManager")
public interface DealManager extends RemoteService{

	List<MatchedDealRecord> getMatchedDeals(String customerName)
			throws Exception;
	
	List<TradeRecord> getTrades(String string) throws Exception;
	
	List<TradeRecord> getCounterpartyTrades(String string) throws Exception;
	
	TradeRecord saveTrade(TradeRecord trade) throws Exception;
	
	void startFeeder(int refreshInterval) throws Exception;
	
	void stopFeeder() throws Exception;
	
	void clearUnmatchedTrades() throws Exception;
}
