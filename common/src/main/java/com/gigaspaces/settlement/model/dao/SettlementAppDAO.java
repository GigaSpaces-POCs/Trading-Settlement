package com.gigaspaces.settlement.model.dao;

import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.settlement.model.MatchedDeal;
import com.gigaspaces.settlement.model.Trade;

public interface SettlementAppDAO {
	MatchedDeal[] getMatchedDeals(String entity);
	
	MatchedDeal[] getMatchedDeals(String entity, String counterparty);

	Trade[] getTrades(String entity, String counterparty);

	SpaceDocument saveDocument(SpaceDocument document);
	
	Trade saveTrade(Trade trade);

	Trade[] getCounterpartyTrades(String entity, String counterparty);

	void clearUnmatchedTrades();
}
