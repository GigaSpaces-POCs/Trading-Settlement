package com.gigaspaces.settlement.model;

import java.util.Arrays;
import java.util.Date;

import com.gigaspaces.document.SpaceDocument;

public class OptionMatchedDeal extends SpaceDocument {

	public OptionMatchedDeal() {
		super("OptionMatchedDealDocument");
	}
	
	public OptionMatchedDeal(OptionTrade optionTrade, OptionTrade matchingOptionTrade) {
		super("OptionMatchedDealDocument");
		if (optionTrade.getBuySellFlag().equals("B")) {
			setProperty("BuySideEntity", optionTrade.getTradingParty());
			setProperty("SellSideEntity", optionTrade.getCounterparty());
			setProperty("BuySideAmount", optionTrade.getAmount());
			setProperty("BuySideAccount", optionTrade.getAccount());
			setProperty("SellSideAmount", matchingOptionTrade.getAmount());
			setProperty("SellSideAccount", matchingOptionTrade.getAccount());
			setProperty("Reference", optionTrade.getReference());
		} else {
			setProperty("BuySideEntity", optionTrade.getCounterparty());
			setProperty("SellSideEntity", optionTrade.getTradingParty());
			setProperty("SellSideAmount", optionTrade.getAmount());
			setProperty("SellSideAccount", optionTrade.getAccount());
			setProperty("BuySideAmount", matchingOptionTrade.getAmount());
			setProperty("BuySideAccount", matchingOptionTrade.getAccount());
			setProperty("Reference", matchingOptionTrade.getReference());
		}
		optionTrade.setMatched(true);
		matchingOptionTrade.setMatched(true);
		
		setProperty("Instrument", optionTrade.getInstrument());
		setProperty("ValueDate", optionTrade.getValueDate());
		setProperty("MatchDate", new Date());
		setProperty("OptionType", optionTrade.getOptionType());
		setProperty("StrikePrice", optionTrade.getStrikePrice());
		setProperty("OptionExpirationDate", optionTrade.getOptionExpirationDate());
		
		String[] entities = new String[] {getBuySideEntity(), getSellSideEntity()};
		Arrays.sort(entities);
		String routing = entities[0] + "-" + entities[1];
		setProperty("Routing", routing);
		
	}

	public String getDealId() {
		return getProperty("DealId");
	}

	public OptionMatchedDeal setDealId(String dealId) {
		setProperty("DealId", dealId);
		return this;
	}
	
	public String getReference() {
		return getProperty("Reference");
	}
	
	public OptionMatchedDeal setReference(String reference) {
		setProperty("Reference", reference);
		return this;
	}

	public String getBuySideEntity() {
		return getProperty("BuySideEntity");
	}

	public OptionMatchedDeal setBuySideEntity(String buySideEntity) {
		setProperty("BuySideEntity", buySideEntity);
		return this;
	}

	public String getSellSideEntity() {
		return getProperty("SellSideEntity");
	}

	public OptionMatchedDeal setSellSideEntity(String sellSideEntity) {
		setProperty("SellSideEntity", sellSideEntity);
		return this;
	}

	public String getInstrument() {
		return getProperty("Instrument");
	}

	public OptionMatchedDeal setInstrument(String instrument) {
		setProperty("Instrument", instrument);
		return this;
	}

	public String getOptionType() {
		return getProperty("OptionType");
	}

	public OptionMatchedDeal setOptionType(String optionType) {
		setProperty("OptionType", optionType);
		return this;
	}

	public OptionMatchedDeal setStrikePrice(Float strikePrice) {
		setProperty("StrikePrice", strikePrice);
		return this;
	}

	public Float getStrikePrice() {
		return getProperty("StrikePrice");
	}
	
	public Date getOptionExpirationDate() {
		return getProperty("OptionExpirationDate");
	}
	
	public OptionMatchedDeal setOptionExpirationDate(Date optionExpirationDate) {
		setProperty("OptionExpirationDate", optionExpirationDate);
		return this;
	}

	public Double getBuySideAmount() {
		return getProperty("BuySideAmount");
	}

	public OptionMatchedDeal setBuySideAmount(Double buySideAmount) {
		setProperty("BuySideAmount", buySideAmount);
		return this;
	}

	public Double getSellSideAmount() {
		return getProperty("SellSideAmount");
	}

	public OptionMatchedDeal setSellSideAmount(Double sellSideAmount) {
		setProperty("SellSideAmount", sellSideAmount);
		return this;
	}

	public String getBuySideAccount() {
		return getProperty("BuySideAccount");
	}

	public OptionMatchedDeal setBuySideAccount(String buySideAccount) {
		setProperty("BuySideAccount", buySideAccount);
		return this;
	}

	public String getSellSideAccount() {
		return getProperty("SellSideAccount");
	}

	public OptionMatchedDeal setSellSideAccount(String sellSideAccount) {
		setProperty("SellSideAccount", sellSideAccount);
		return this;
	}

	public Date getValueDate() {
		return getProperty("ValueDate");
	}

	public OptionMatchedDeal setValueDate(Date valueDate) {
		setProperty("ValueDate", valueDate);
		return this;
	}

	public String getRouting() {
		return getProperty("Routing");
	}
	
	public OptionMatchedDeal setRouting(String routing) {
		setProperty("Routing", routing);
		return this;
	}
	
	public String getXmlMessage() {
		return getProperty("XmlMessage");
	}
	
	public OptionMatchedDeal setXmlMessage(String message) {
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
	
	public OptionMatchedDeal setMatchDate(Date date) {
		setProperty("MatchDate", date);
		return this;
	}
}
