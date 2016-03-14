package com.gigaspaces.settlement;

import java.util.List;

import org.openspaces.remoting.Routing;

import com.gigaspaces.settlement.model.MatchedDeal;
import com.gigaspaces.settlement.model.Trade;

public interface SettlementAppService {
	
	List<MatchedDeal> getMatchedDeals(String entity, String counterparty) throws Exception;
	
	List<MatchedDeal> getMatchedDeals(String entity) throws Exception;
	
	List<Trade> getTrades(String entity, String counterparty) throws Exception;
	
	Trade saveTrade(@Routing("getRouting")Trade trade) throws Exception;

	List<Trade> getCounterpartyTrades(String entity, String counterparty) throws Exception;

	void clearUnmatchedTrades() throws Exception;
	
}
