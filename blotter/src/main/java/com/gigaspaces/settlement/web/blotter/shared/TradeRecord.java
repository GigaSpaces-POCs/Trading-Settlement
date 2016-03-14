package com.gigaspaces.settlement.web.blotter.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;


public class TradeRecord implements IsSerializable {

	private String tradeId;
	private String reference;
	private String tradingParty;
	private String counterparty;
	private String instrument;
	private Boolean processed = false;
	private String buySellFlag;
	private Double amount;
	private String account;
	private Date valueDate;
	private Date tradeDate;
	private String routing;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}

	public String getTradingParty() {
		return tradingParty;
	}

	public void setTradingParty(String tradingParty) {
		this.tradingParty = tradingParty;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public String getBuySellFlag() {
		return buySellFlag;
	}

	public void setBuySellFlag(String buySellFlag) {
		this.buySellFlag = buySellFlag;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeRecord [tradeId=");
		builder.append(tradeId);
		builder.append(", reference=");
		builder.append(reference);
		builder.append(", tradingParty=");
		builder.append(tradingParty);
		builder.append(", counterparty=");
		builder.append(counterparty);
		builder.append(", instrument=");
		builder.append(instrument);
		builder.append(", processed=");
		builder.append(processed);
		builder.append(", buySellFlag=");
		builder.append(buySellFlag);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", account=");
		builder.append(account);
		builder.append(", valueDate=");
		builder.append(valueDate);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", routing=");
		builder.append(routing);
		builder.append("]");
		return builder.toString();
	}

	
}
