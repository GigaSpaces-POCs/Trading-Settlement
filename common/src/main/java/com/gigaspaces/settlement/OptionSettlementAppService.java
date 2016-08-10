package com.gigaspaces.settlement;

import java.util.List;

import org.openspaces.remoting.Routing;

import com.gigaspaces.settlement.model.OptionMatchedDeal;
import com.gigaspaces.settlement.model.OptionTrade;

public interface OptionSettlementAppService {
	
	List<OptionMatchedDeal> getOptionMatchedDeals(String entity, String counterparty) throws Exception;
	
	List<OptionMatchedDeal> getOptionMatchedDeals(String entity) throws Exception;
	
	List<OptionTrade> getOptionTrades(String entity, String counterparty) throws Exception;
	
	OptionTrade saveOptionTrade(@Routing("getRouting")OptionTrade optionTrade) throws Exception;

	List<OptionTrade> getCounterpartyOptionTrades(String entity, String counterparty) throws Exception;

	void clearUnmatchedOptionTrades() throws Exception;
	
}
