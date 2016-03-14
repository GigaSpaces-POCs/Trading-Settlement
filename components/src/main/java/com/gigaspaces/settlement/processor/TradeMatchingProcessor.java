package com.gigaspaces.settlement.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;

import com.gigaspaces.settlement.model.MatchedDeal;
import com.gigaspaces.settlement.model.Trade;
import com.j_spaces.core.client.SQLQuery;

/*
 * This class will take deals out of the space that are unmatched
 * and try and match values with other deals
 */

@EventDriven
@Polling(gigaSpace = "gigaSpace")
@TransactionalEvent(transactionManager="transactionManager")
public class TradeMatchingProcessor {
	
	private static Log LOG = LogFactory.getLog(TradeMatchingProcessor.class);

	@GigaSpaceContext(name = "gigaSpace")
    transient GigaSpace gigaSpace;
	
	@EventTemplate
	public SQLQuery<Trade> getUnprocessedTrade() {
		SQLQuery<Trade> template = new SQLQuery<Trade>(Trade.class, "processed = false");
        return template;
	}
	
	@SpaceDataEvent
	public Trade onEvent(Trade trade) throws Exception {
		LOG.info("Processing trade: " + trade.getReference() + " at " + System.currentTimeMillis());
		SQLQuery<Trade> template = 
			new SQLQuery<Trade>(Trade.class, "instrument = '" + trade.getInstrument() + 
					"' and counterparty = '" + trade.getTradingParty() + 
					"' and tradingParty = '" + trade.getCounterparty() + 
					"' and matched = false and 	buySellFlag = " +
					(trade.getBuySellFlag().equals("B") ? "'S'" : "'B'"));
		
		LOG.info("SQL Query: " + template);
		Trade[] matchingTrades = gigaSpace.readMultiple(template, Integer.MAX_VALUE);
		if (matchingTrades == null || matchingTrades.length == 0) {
			LOG.info("No trades to match with");
		} else {
			LOG.info("Matching trades " + trade.getReference() + " and " + matchingTrades[0].getReference());
			// Create the match
			MatchedDeal deal = new MatchedDeal(trade, matchingTrades[0]);
			gigaSpace.write(deal);
			gigaSpace.write(trade);
			
			matchingTrades[0].setMatched(true);
			trade.setMatched(true);
			
			gigaSpace.write(matchingTrades[0]);
		}
		
		trade.setProcessed(true);
		
		LOG.info("Completed processing trade: " + trade.getReference() + " at " + System.currentTimeMillis());
		
		return trade;
	}
}
