package com.gigaspaces.settlement.web.blotter.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MatchedDealRecord implements IsSerializable {

	private String dealId;
	private String reference;
	private String matchState;
	private String buySideEntity;
	private String sellSideEntity;
	private String instrument;
	private Double buySideAmount;
	private Double sellSideAmount;
	private String buySideAccount;
	private String sellSideAccount;
	private Date valueDate;
	private Date matchDate;
	private String xmlMessage;
	private Date messageGenerationDate;
	
	private String routing;
	

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getMatchState() {
		return matchState;
	}

	public void setMatchState(String matchState) {
		this.matchState = matchState;
	}

	public String getBuySideEntity() {
		return buySideEntity;
	}

	public void setBuySideEntity(String buySideEntity) {
		this.buySideEntity = buySideEntity;
	}

	public String getSellSideEntity() {
		return sellSideEntity;
	}

	public void setSellSideEntity(String sellSideEntity) {
		this.sellSideEntity = sellSideEntity;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public Double getBuySideAmount() {
		return buySideAmount;
	}

	public void setBuySideAmount(Double buySideAmount) {
		this.buySideAmount = buySideAmount;
	}

	public Double getSellSideAmount() {
		return sellSideAmount;
	}

	public void setSellSideAmount(Double sellSideAmount) {
		this.sellSideAmount = sellSideAmount;
	}

	public String getBuySideAccount() {
		return buySideAccount;
	}

	public void setBuySideAccount(String buySideAccount) {
		this.buySideAccount = buySideAccount;
	}

	public String getSellSideAccount() {
		return sellSideAccount;
	}

	public void setSellSideAccount(String sellSideAccount) {
		this.sellSideAccount = sellSideAccount;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getRouting() {
		return routing;
	}
	
	public void setRouting(String routing) {
		this.routing = routing;
	}

	public void setXmlMessage(String xmlMessage) {
		this.xmlMessage = xmlMessage;
	}

	public String getXmlMessage() {
		return xmlMessage;
	}

	public void setMessageGeneratedDate(Date messageGenerationDate) {
		this.messageGenerationDate = messageGenerationDate;
	}

	public Date getMessageGeneratedDate() {
		return messageGenerationDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}
}
