package com.gigaspaces.settlement;

import java.util.Date;
import java.util.List;

import org.openspaces.remoting.RemotingService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigaspaces.document.DocumentProperties;
import com.gigaspaces.document.SpaceDocument;
import java.util.Arrays;
import com.gigaspaces.settlement.model.MatchedDeal;
import com.gigaspaces.settlement.model.Trade;
import com.gigaspaces.settlement.model.dao.SettlementAppDAO;

@RemotingService
public class SettlementAppServiceImpl implements SettlementAppService {

	@Autowired
	private SettlementAppDAO settlementAppDao;
	
	public void setSettlementAppDao(SettlementAppDAO settlementAppDao) {
		this.settlementAppDao = settlementAppDao;
	}

	public SettlementAppDAO getSettlementAppDao() {
		return settlementAppDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MatchedDeal> getMatchedDeals(String entity, String counterparty) throws Exception {
		MatchedDeal[] deals = getSettlementAppDao().getMatchedDeals(entity, counterparty);
		return Arrays.asList(deals);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MatchedDeal> getMatchedDeals(String entity) throws Exception {
		MatchedDeal[] deals = getSettlementAppDao().getMatchedDeals(entity);
		return Arrays.asList(deals);
	}

	/*
	 * Search for trades for the user who is logged into the system.  it will filter by a specific 
	 * counterparty if specified
	 * 
	 * (non-Javadoc)
	 * @see com.gigaspaces.settlement.SettlementAppService#getTrades(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Trade> getTrades(String entity, String counterparty) throws Exception {
		Trade[] trades = getSettlementAppDao().getTrades(entity, counterparty);
		return Arrays.asList(trades);
	}
	
	/*
	 * The entity in this case is the entity that is logged into the system. 
	 * This will filter the results for a particular counterparty of that entity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Trade> getCounterpartyTrades(String entity, String counterparty) throws Exception {
		Trade[] trades = getSettlementAppDao().getCounterpartyTrades(entity, counterparty);
		return Arrays.asList(trades);
	}
	
	public SpaceDocument saveTradeDocument() {
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
        return getSettlementAppDao().saveDocument(document);
	}
	
	public Trade saveTrade(Trade trade) throws Exception {
		return getSettlementAppDao().saveTrade(trade);
	}
	
	public void clearUnmatchedTrades() throws Exception {
		getSettlementAppDao().clearUnmatchedTrades();
	}

}
