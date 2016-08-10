package com.gigaspaces.settlement.web.blotter.shared;

import static com.gigaspaces.settlement.web.blotter.shared.Constants.*;

import java.util.Date;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ListGridTradeRecord extends ListGridRecord {

	public ListGridTradeRecord(TradeRecord tradeRecord) {
		setAttribute(TRADE_ID, tradeRecord.getTradeId());
		setAttribute(TRADING_PARTY, tradeRecord.getTradingParty());
		setAttribute(REFERENCE, tradeRecord.getReference());
		setAttribute(CPTY, tradeRecord.getCounterparty());
		setAttribute(INSTRUMENT, tradeRecord.getInstrument());
		setAttribute(BUY_SELL_FLAG, tradeRecord.getBuySellFlag());
		setAttribute(AMOUNT, tradeRecord.getAmount());
		setAttribute(ACCOUNT, tradeRecord.getAccount());
		setAttribute(TRADE_DATE, tradeRecord.getTradeDate());
		setAttribute(VALUE_DATE, tradeRecord.getValueDate());
	}

	public String getTradeId() {
		return getAttributeAsString(DEAL_ID);
	}

	public void setTradeId(String tradeId) {
		setAttribute(TRADE_ID, tradeId);
	}

	public String getTradingParty() {
		return getAttributeAsString(TRADING_PARTY);
	}

	public void setTradingParty(String tradingParty) {
		setAttribute(TRADING_PARTY, tradingParty);
	}

	public String getCounterparty() {
		return getAttributeAsString(CPTY);
	}

	public void setCounterparty(String counterparty) {
		setAttribute(CPTY, counterparty);
	}
	
	public String getReference() {
		return getAttributeAsString(REFERENCE);
	}
	
	public void setReference(String reference) {
		setAttribute(REFERENCE, reference);
	}

	public String getInstrument() {
		return getAttributeAsString(INSTRUMENT);
	}

	public void setInstrument(String instrument) {
		setAttribute(INSTRUMENT, instrument);
	}

	public String getBuySellFlag() {
		return getAttributeAsString(BUY_SELL_FLAG);
	}

	public void setBuySellFlag(String buySellFlag) {
		setAttribute(BUY_SELL_FLAG, buySellFlag);
	}

	public Float getAmount() {
		return getAttributeAsFloat(AMOUNT);
	}

	public void setAmount(Double amount) {
		setAttribute(AMOUNT, amount);
	}

	public String getAccount() {
		return getAttributeAsString(ACCOUNT);
	}

	public void setAccount(String account) {
		setAttribute(ACCOUNT, account);
	}

	public Date getValueDate() {
		return getAttributeAsDate(VALUE_DATE);
	}

	public void setValueDate(Date valueDate) {
		setAttribute(VALUE_DATE, valueDate);
	}

	public Date getTradeDate() {
		return getAttributeAsDate(TRADE_DATE);
	}

	public void setTradeDate(Date tradeDate) {
		setAttribute(TRADE_DATE, tradeDate);
	}

	public static ListGridField[] getFields() {
		ListGridField tradingPartyField = new ListGridField(TRADING_PARTY,TRADING_PARTY);
		ListGridField counterpartyField = new ListGridField(CPTY, CPTY);
		ListGridField reference = new ListGridField(REFERENCE, REFERENCE);
		ListGridField instrument = new ListGridField(INSTRUMENT, INSTRUMENT);
		ListGridField buySellFlag = new ListGridField(BUY_SELL_FLAG, BUY_SELL_FLAG);
		ListGridField amount = new ListGridField(AMOUNT, AMOUNT);
		amount.setType(ListGridFieldType.FLOAT);
		ListGridField account = new ListGridField(ACCOUNT, ACCOUNT);
		ListGridField tradeDate = new ListGridField(TRADE_DATE, TRADE_DATE);
		tradeDate.setType(ListGridFieldType.DATE);
		ListGridField valueDate = new ListGridField(VALUE_DATE, VALUE_DATE);
		tradeDate.setType(ListGridFieldType.DATE);
 
		return new ListGridField[] {tradingPartyField, counterpartyField,
				reference, instrument,buySellFlag, amount, account, tradeDate, valueDate };
	}

	
}
