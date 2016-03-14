package com.gigaspaces.settlement.model;

import java.util.Arrays;
import java.util.Date;

import com.gigaspaces.document.SpaceDocument;

public class MatchedDeal extends SpaceDocument {

	public MatchedDeal() {
		super("MatchedDealDocument");
	}
	
	public MatchedDeal(Trade trade, Trade matchingTrade) {
		super("MatchedDealDocument");
		if (trade.getBuySellFlag().equals("B")) {
			setProperty("BuySideEntity", trade.getTradingParty());
			setProperty("SellSideEntity", trade.getCounterparty());
			setProperty("BuySideAmount", trade.getAmount());
			setProperty("BuySideAccount", trade.getAccount());
			setProperty("SellSideAmount", matchingTrade.getAmount());
			setProperty("SellSideAccount", matchingTrade.getAccount());
			setProperty("Reference", trade.getReference());
		} else {
			setProperty("BuySideEntity", trade.getCounterparty());
			setProperty("SellSideEntity", trade.getTradingParty());
			setProperty("SellSideAmount", trade.getAmount());
			setProperty("SellSideAccount", trade.getAccount());
			setProperty("BuySideAmount", matchingTrade.getAmount());
			setProperty("BuySideAccount", matchingTrade.getAccount());
			setProperty("Reference", matchingTrade.getReference());
		}
		trade.setMatched(true);
		matchingTrade.setMatched(true);
		
		setProperty("Instrument", trade.getInstrument());
		setProperty("ValueDate", trade.getValueDate());
		setProperty("MatchDate", new Date());
		
		String[] entities = new String[] {getBuySideEntity(), getSellSideEntity()};
		Arrays.sort(entities);
		String routing = entities[0] + "-" + entities[1];
		setProperty("Routing", routing);
		
	}

	public String getDealId() {
		return getProperty("DealId");
	}

	public MatchedDeal setDealId(String dealId) {
		setProperty("DealId", dealId);
		return this;
	}
	
	public String getReference() {
		return getProperty("Reference");
	}
	
	public MatchedDeal setReference(String reference) {
		setProperty("Reference", reference);
		return this;
	}

	public String getBuySideEntity() {
		return getProperty("BuySideEntity");
	}

	public MatchedDeal setBuySideEntity(String buySideEntity) {
		setProperty("BuySideEntity", buySideEntity);
		return this;
	}

	public String getSellSideEntity() {
		return getProperty("SellSideEntity");
	}

	public MatchedDeal setSellSideEntity(String sellSideEntity) {
		setProperty("SellSideEntity", sellSideEntity);
		return this;
	}

	public String getInstrument() {
		return getProperty("Instrument");
	}

	public MatchedDeal setInstrument(String instrument) {
		setProperty("Instrument", instrument);
		return this;
	}

	public Double getBuySideAmount() {
		return getProperty("BuySideAmount");
	}

	public MatchedDeal setBuySideAmount(Double buySideAmount) {
		setProperty("BuySideAmount", buySideAmount);
		return this;
	}

	public Double getSellSideAmount() {
		return getProperty("SellSideAmount");
	}

	public MatchedDeal setSellSideAmount(Double sellSideAmount) {
		setProperty("SellSideAmount", sellSideAmount);
		return this;
	}

	public String getBuySideAccount() {
		return getProperty("BuySideAccount");
	}

	public MatchedDeal setBuySideAccount(String buySideAccount) {
		setProperty("BuySideAccount", buySideAccount);
		return this;
	}

	public String getSellSideAccount() {
		return getProperty("SellSideAccount");
	}

	public MatchedDeal setSellSideAccount(String sellSideAccount) {
		setProperty("SellSideAccount", sellSideAccount);
		return this;
	}

	public Date getValueDate() {
		return getProperty("ValueDate");
	}

	public MatchedDeal setValueDate(Date valueDate) {
		setProperty("ValueDate", valueDate);
		return this;
	}

	public String getRouting() {
		return getProperty("Routing");
	}
	
	public MatchedDeal setRouting(String routing) {
		setProperty("Routing", routing);
		return this;
	}
	
	public String getXmlMessage() {
		return getProperty("XmlMessage");
	}
	
	public MatchedDeal setXmlMessage(String message) {
		setProperty("XmlMessage", message);
		return this;
	}
	
	public Date getMessageGeneratedDate() {
		return getProperty("MessageGeneratedDate");
	}
	
	public void setMessageGeneratedDate(Date date) {
		setProperty("MessageGeneratedDate", date);
	}
	
	public Date getMatchDate() {
		return getProperty("MatchDate");
	}
	
	public MatchedDeal setMatchDate(Date date) {
		setProperty("MatchDate", date);
		return this;
	}
}
