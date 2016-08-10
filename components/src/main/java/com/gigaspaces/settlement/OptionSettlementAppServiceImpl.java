package com.gigaspaces.settlement;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openspaces.remoting.RemotingService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigaspaces.document.DocumentProperties;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.settlement.model.OptionMatchedDeal;
import com.gigaspaces.settlement.model.OptionTrade;
import com.gigaspaces.settlement.model.dao.OptionSettlementAppDAO;

@RemotingService
public class OptionSettlementAppServiceImpl implements OptionSettlementAppService {

	@Autowired
	private OptionSettlementAppDAO optionSettlementAppDao;
	
	public void setOptionSettlementAppDao(OptionSettlementAppDAO optionSettlementAppDao) {
		this.optionSettlementAppDao = optionSettlementAppDao;
	}

	public OptionSettlementAppDAO getOptionSettlementAppDao() {
		return optionSettlementAppDao;
	}

	public List<OptionMatchedDeal> getOptionMatchedDeals(String entity, String counterparty) throws Exception {
		OptionMatchedDeal[] deals = getOptionSettlementAppDao().getOptionMatchedDeals(entity, counterparty);
		return Arrays.asList(deals);
	}

	public List<OptionMatchedDeal> getOptionMatchedDeals(String entity) throws Exception {
		OptionMatchedDeal[] deals = getOptionSettlementAppDao().getOptionMatchedDeals(entity);
		return Arrays.asList(deals);
	}

	/*
	 * Search for trades for the user who is logged into the system.  it will filter by a specific 
	 * counterparty if specified
	 * 
	 * (non-Javadoc)
	 * @see com.gigaspaces.settlement.SettlementAppService#getTrades(java.lang.String, java.lang.String)
	 */
	public List<OptionTrade> getOptionTrades(String entity, String counterparty) throws Exception {
		OptionTrade[] trades = getOptionSettlementAppDao().getOptionTrades(entity, counterparty);
		return Arrays.asList(trades);
	}
	
	/*
	 * The entity in this case is the entity that is logged into the system. 
	 * This will filter the results for a particular counterparty of that entity
	 */
	@Override
	public List<OptionTrade> getCounterpartyOptionTrades(String entity, String counterparty) throws Exception {
		OptionTrade[] trades = getOptionSettlementAppDao().getCounterpartyOptionTrades(entity, counterparty);
		return Arrays.asList(trades);
	}
	
	public SpaceDocument saveOptionTradeDocument() {
		// Now write TradeDocument
        DocumentProperties properties = new DocumentProperties()
        .setProperty("tradeId", 12321)
        .setProperty("routing", "abc")
        .setProperty("partyA", "cust1")
        .setProperty("partyB", "BANK2")
        .setProperty("account", "account1")
        .setProperty("dealtAmount", 19.99f)
        .setProperty("valueDate", new Date())
        .setProperty("routing", 0);
        
        // 2. Create the document using the type name and properties: 
        SpaceDocument document = new SpaceDocument("TradeDocument", properties);
        // 3. Write the document to the space:
        return getOptionSettlementAppDao().saveDocument(document);
	}
	
	public OptionTrade saveOptionTrade(OptionTrade optionTrade) throws Exception {
		return getOptionSettlementAppDao().saveOptionTrade(optionTrade);
	}
	
	public void clearUnmatchedOptionTrades() throws Exception {
		getOptionSettlementAppDao().clearUnmatchedOptionTrades();
	}

}
