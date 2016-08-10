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

import com.gigaspaces.settlement.model.OptionMatchedDeal;
import com.gigaspaces.settlement.model.OptionTrade;
import com.j_spaces.core.client.SQLQuery;

/*
 * This class will take deals out of the space that are unmatched
 * and try and match values with other deals
 */

@EventDriven
@Polling(gigaSpace = "gigaSpace")
@TransactionalEvent(transactionManager="transactionManager")
public class OptionTradeMatchingProcessor {
	
	private static Log LOG = LogFactory.getLog(OptionTradeMatchingProcessor.class);

	@GigaSpaceContext(name = "gigaSpace")
    transient GigaSpace gigaSpace;
	
	@EventTemplate
	public SQLQuery<OptionTrade> getUnprocessedOptionTrade() {
		SQLQuery<OptionTrade> template = new SQLQuery<OptionTrade>(OptionTrade.class, "processed = false");
        return template;
	}
	
	@SpaceDataEvent
	public OptionTrade onEvent(OptionTrade optionTrade) throws Exception {
		LOG.info("Processing optionTrade: " + optionTrade.getReference() + " at " + System.currentTimeMillis());
		/*SQLQuery<OptionTrade> template = 
			new SQLQuery<OptionTrade>(OptionTrade.class, "instrument = '" + optionTrade.getInstrument() + 
					"' and counterparty = '" + optionTrade.getTradingParty() + 
					"' and tradingParty = '" + optionTrade.getCounterparty() + 
					"' and optionExpirationDate = '" + optionTrade.getOptionExpirationDate() + 
					"' and strikePrice = " + optionTrade.getStrikePrice() + 
					" and optionType = '" + optionTrade.getOptionType() + 
					"' and matched = false and buySellFlag = " +
					(optionTrade.getBuySellFlag().equals("B") ? "'S'" : "'B'"));*/
		SQLQuery<OptionTrade> template = 
				new SQLQuery<OptionTrade>(OptionTrade.class, "instrument = ? and counterparty = ? and tradingParty = ?" + 
						" and optionExpirationDate = ? and strikePrice = ? and optionType = ? and matched = false and buySellFlag = ?")
						.setParameter(1, optionTrade.getInstrument())
						.setParameter(2, optionTrade.getTradingParty())
						.setParameter(3, optionTrade.getCounterparty())
						.setParameter(4, optionTrade.getOptionExpirationDate())
						.setParameter(5, optionTrade.getStrikePrice())
						.setParameter(6, optionTrade.getOptionType())
						.setParameter(7, optionTrade.getBuySellFlag().equals("B") ? "'S'" : "'B'");
		
		LOG.info("SQL Query: " + template);
		OptionTrade[] matchingOptionTrades = gigaSpace.readMultiple(template, Integer.MAX_VALUE);
		if (matchingOptionTrades == null || matchingOptionTrades.length == 0) {
			LOG.info("No optionTrades to match with " + optionTrade.toString());
		} else {
			LOG.info("Matching optionTrades " + optionTrade.getReference() + " and " + matchingOptionTrades[0].getReference());
			// Create the match
			OptionMatchedDeal deal = new OptionMatchedDeal(optionTrade, matchingOptionTrades[0]);
			gigaSpace.write(deal);
			gigaSpace.write(optionTrade);
			
			matchingOptionTrades[0].setMatched(true);
			optionTrade.setMatched(true);
			
			gigaSpace.write(matchingOptionTrades[0]);
		}
		
		optionTrade.setProcessed(true);
		
		LOG.info("Completed processing optionTrade: " + optionTrade.getReference() + " at " + System.currentTimeMillis());
		
		return optionTrade;
	}
}
