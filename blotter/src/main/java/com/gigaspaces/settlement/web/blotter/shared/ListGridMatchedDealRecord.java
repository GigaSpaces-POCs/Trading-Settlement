package com.gigaspaces.settlement.web.blotter.shared;

import static com.gigaspaces.settlement.web.blotter.shared.Constants.*;


import java.util.Date;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ListGridMatchedDealRecord extends ListGridRecord {

	public ListGridMatchedDealRecord() { }

	public ListGridMatchedDealRecord(MatchedDealRecord dealRecord) {
		setDealId(dealRecord.getDealId());
		setBuySideEntity(dealRecord.getBuySideEntity());
		setSellSideEntity(dealRecord.getSellSideEntity());
		setReference(dealRecord.getReference());
		setInstrument(dealRecord.getInstrument());
		setBuySideAccount(dealRecord.getBuySideAccount());
		setSellSideAccount(dealRecord.getSellSideAccount());
		setBuySideAmount(dealRecord.getBuySideAmount());
		setSellSideAmount(dealRecord.getSellSideAmount());
		setValueDate(dealRecord.getValueDate());
		setMatchDate(dealRecord.getMatchDate());
		setXmlMessage(dealRecord.getXmlMessage());
		setMessageGeneratedDate(dealRecord.getMessageGeneratedDate());
	}

	public void setReference(String reference) {
		setAttribute(REFERENCE, reference);
	}

	public void setMessageGeneratedDate(Date messageGenerationDate) {
		setAttribute(MESSAGE_GENERATION_DATE, messageGenerationDate);
	}

	public void setXmlMessage(String xmlMessage) {
		setAttribute(XML_MESSAGE, xmlMessage);
	}

	private void setMatchDate(Date matchDate) {
		setAttribute(MATCH_DATE, matchDate);
	}

	public Double getSellSideAmount() {
		return getAttributeAsDouble(SELL_SIDE_AMOUNT);
	}

	private void setSellSideAmount(Double sellSideAmount) {
		setAttribute(SELL_SIDE_AMOUNT, sellSideAmount);
	}

	private void setSellSideAccount(String sellSideAccount) {
		setAttribute(SELL_SIDE_ACCOUNT, sellSideAccount);
	}

	private void setBuySideAmount(Double buySideAmount) {
		setAttribute(BUY_SIDE_AMOUNT, buySideAmount);
	}

	private void setBuySideAccount(String buySideAccount) {
		setAttribute(BUY_SIDE_ACCOUNT, buySideAccount);
	}

	private void setSellSideEntity(String sellSideEntity) {
		setAttribute(SELL_SIDE_ENTITY, sellSideEntity);
	}
	
	private void setBuySideEntity(String buySideEntity) {
		setAttribute(BUY_SIDE_ENTITY, buySideEntity);
	}
	
	private void setInstrument(String instrument) {
		setAttribute(INSTRUMENT, instrument);
	}

	public String getDealId() {
		return getAttributeAsString(DEAL_ID);
	}

	public void setDealId(String dealId) {
		setAttribute(DEAL_ID, dealId);
	}

	public String getValueDate() {
		return getAttributeAsString(VALUE_DATE);
	}

	public void setValueDate(Date date) {
		setAttribute(VALUE_DATE, date);
	}
	
	public static ListGridField[] getFields() {
		ListGridField buySideEntity = new ListGridField(BUY_SIDE_ENTITY,BUY_SIDE_ENTITY);
		ListGridField sellSideEntity = new ListGridField(SELL_SIDE_ENTITY, SELL_SIDE_ENTITY);
		ListGridField reference = new ListGridField(REFERENCE, REFERENCE);
		ListGridField instrument = new ListGridField(INSTRUMENT, INSTRUMENT);
		ListGridField buySideAccount = new ListGridField(BUY_SIDE_ACCOUNT, BUY_SIDE_ACCOUNT);
		ListGridField sellSideAccount = new ListGridField(SELL_SIDE_ACCOUNT, SELL_SIDE_ACCOUNT);
		ListGridField buySideAmount = new ListGridField(BUY_SIDE_AMOUNT, BUY_SIDE_AMOUNT);
		buySideAmount.setType(ListGridFieldType.FLOAT);
		ListGridField sellSideAmount = new ListGridField(SELL_SIDE_AMOUNT, SELL_SIDE_AMOUNT);
		sellSideAmount.setType(ListGridFieldType.FLOAT);
		ListGridField valueDate = new ListGridField(VALUE_DATE, VALUE_DATE);
		valueDate.setType(ListGridFieldType.DATE);
		ListGridField matchDate = new ListGridField(MATCH_DATE, MATCH_DATE);
		matchDate.setType(ListGridFieldType.DATE);
		ListGridField xmlMessage = new ListGridField(XML_MESSAGE, XML_MESSAGE);
		ListGridField messageGenerationDate = new ListGridField(MESSAGE_GENERATION_DATE, MESSAGE_GENERATION_DATE);
		
		return new ListGridField[] {buySideEntity, sellSideEntity, reference, instrument, buySideAccount, sellSideAccount, 
			buySideAmount, sellSideAmount, valueDate, matchDate, xmlMessage, messageGenerationDate };
	}
}
