package com.gigaspaces.settlement.model.dao.gigaspaces;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.beans.factory.InitializingBean;

import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.metadata.SpaceTypeDescriptor;
import com.gigaspaces.metadata.SpaceTypeDescriptorBuilder;
import com.gigaspaces.metadata.index.SpaceIndexType;
import com.gigaspaces.settlement.model.OptionMatchedDeal;
import com.gigaspaces.settlement.model.OptionTrade;
import com.gigaspaces.settlement.model.dao.OptionSettlementAppDAO;
import com.j_spaces.core.client.SQLQuery;

public class OptionSettlementAppDAOGigaSpaces implements InitializingBean, OptionSettlementAppDAO {

	@GigaSpaceContext(name = "gigaSpace")
	private GigaSpace gigaSpace;

	public void setGigaSpace(GigaSpace gigaSpace) {
		this.gigaSpace = gigaSpace;
	}

	public GigaSpace getGigaSpace() {
		return gigaSpace;
	}

	@Override
	public OptionMatchedDeal[] getOptionMatchedDeals(String entity) {
		SQLQuery<OptionMatchedDeal> sqlQuery = new SQLQuery<OptionMatchedDeal>(
				"OptionMatchedDealDocument", "BuySideEntity = ? or SellSideEntity = ?");
		sqlQuery.setParameter(1, entity);
		sqlQuery.setParameter(2, entity);

		return getGigaSpace().readMultiple(sqlQuery, Integer.MAX_VALUE);
	}
	
	@Override
	public OptionMatchedDeal[] getOptionMatchedDeals(String entity, String counterparty) {
		SQLQuery<OptionMatchedDeal> sqlQuery = new SQLQuery<OptionMatchedDeal>(
				"OptionMatchedDealDocument", 
				"(BuySideEntity = ? and SellSideEntity = ?) or (BuySideEntity = ? and SellSideEntity = ?) order by MatchDate");
		sqlQuery.setParameter(1, entity);
		sqlQuery.setParameter(2, counterparty);
		sqlQuery.setParameter(3, counterparty);
		sqlQuery.setParameter(4, entity);

		return getGigaSpace().readMultiple(sqlQuery, Integer.MAX_VALUE);
	}

	public void updateDeal(OptionMatchedDeal deal) {
		getGigaSpace().write(deal);
	}

	@Override
	public OptionTrade[] getOptionTrades(String entity, String counterparty) {
		String query = "tradingParty = ? and matched = ?";
		if (counterparty != null) {
			query += " and counterparty = ?";
		}
		query += " order by tradeDate";
		SQLQuery<OptionTrade> sqlQuery = new SQLQuery<OptionTrade>(OptionTrade.class, query);
		sqlQuery.setParameter(1, entity);
		sqlQuery.setParameter(2, false);
		if (counterparty != null) {
			sqlQuery.setParameter(3, counterparty);
		}

		return getGigaSpace().readMultiple(sqlQuery, Integer.MAX_VALUE);
	}
	
	@Override
	public OptionTrade[] getCounterpartyOptionTrades(String entity, String counterparty) {
		String query = "counterparty = ? and matched = ?";
		if (counterparty != null) {
			query += " and tradingParty = ?";
		}
		query += " order by tradeDate";
		SQLQuery<OptionTrade> sqlQuery = new SQLQuery<OptionTrade>(OptionTrade.class,query);
		sqlQuery.setParameter(1, entity);
		sqlQuery.setParameter(2, false);
		if (counterparty != null) {
			sqlQuery.setParameter(3, counterparty);
		}

		return getGigaSpace().readMultiple(sqlQuery, Integer.MAX_VALUE);
	}

	@Override
	public SpaceDocument saveDocument(SpaceDocument document) {
		getGigaSpace().write(document);
		return document;
	}

	@Override
	public OptionTrade saveOptionTrade(OptionTrade trade) {
		getGigaSpace().write(trade);
		return trade;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// Register Type - probably can be done elsewhere
		SpaceTypeDescriptor typeDescriptor = new SpaceTypeDescriptorBuilder(
				"OptionMatchedDealDocument").idProperty("DealId", true)
				.routingProperty("Routing")
				.addPropertyIndex("BuySideEntity", SpaceIndexType.BASIC)
				.addPropertyIndex("SellSideEntity", SpaceIndexType.BASIC)
				.documentWrapperClass(OptionMatchedDeal.class).create();
		// Register type:
		gigaSpace.getTypeManager().registerTypeDescriptor(typeDescriptor);
	}

	@Override
	public void clearUnmatchedOptionTrades() {
		OptionTrade template = new OptionTrade();
        template.setMatched(null);
        template.setProcessed(null);
		gigaSpace.clear(template);
	}
}
