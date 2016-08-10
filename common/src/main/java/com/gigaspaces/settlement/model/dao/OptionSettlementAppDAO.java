package com.gigaspaces.settlement.model.dao;

import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.settlement.model.OptionMatchedDeal;
import com.gigaspaces.settlement.model.OptionTrade;

public interface OptionSettlementAppDAO {
	OptionMatchedDeal[] getOptionMatchedDeals(String entity);
	
	OptionMatchedDeal[] getOptionMatchedDeals(String entity, String counterparty);

	OptionTrade[] getOptionTrades(String entity, String counterparty);

	SpaceDocument saveDocument(SpaceDocument document);
	
	OptionTrade saveOptionTrade(OptionTrade optionTrade);

	OptionTrade[] getCounterpartyOptionTrades(String entity, String counterparty);

	void clearUnmatchedOptionTrades();
}
