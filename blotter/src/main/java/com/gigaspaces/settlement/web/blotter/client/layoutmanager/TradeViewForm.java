package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class TradeViewForm extends DynamicForm {

	private TextItem tradingParty, reference, cpty, instrument, amount, account;
	private SelectItem buySellFlag;
	private DateTimeItem valueDate;
	private ButtonItem saveTradeButton;
	
	public TradeViewForm() {
		tradingParty = new TextItem("tradingParty", "Trading Party");
		reference = new TextItem("reference", "Reference");
		cpty = new TextItem("counterparty", "Counterparty");
		instrument = new TextItem("instrument", "Instrument");
		buySellFlag = new SelectItem("buySellFlag", "Buy/Sell");
		buySellFlag.setValueMap("B", "S");
		amount = new TextItem("amount", "Amount");
		account = new TextItem("account", "Account");
		valueDate = new DateTimeItem("valueDate", "Value Date");
		valueDate.setDisplayFormat(DateDisplayFormat.TOUSSHORTDATE);
		saveTradeButton = new ButtonItem("Save Trade");

		setItems(tradingParty, reference, cpty, instrument, buySellFlag,
				amount, account, valueDate, saveTradeButton);
	}
	
	public void addSaveListener(ClickHandler handler) {
		saveTradeButton.addClickHandler(handler);
    }

	public TextItem getTradingParty() {
		return tradingParty;
	}

	public void setTradingParty(TextItem tradingParty) {
		this.tradingParty = tradingParty;
	}

	public void setReference(TextItem reference) {
		this.reference = reference;
	}

	public TextItem getReference() {
		return reference;
	}

	public TextItem getCpty() {
		return cpty;
	}

	public void setCpty(TextItem cpty) {
		this.cpty = cpty;
	}

	public TextItem getInstrument() {
		return instrument;
	}

	public void setInstrument(TextItem instrument) {
		this.instrument = instrument;
	}

	public TextItem getAmount() {
		return amount;
	}

	public void setAmount(TextItem amount) {
		this.amount = amount;
	}

	public TextItem getAccount() {
		return account;
	}

	public void setAccount(TextItem account) {
		this.account = account;
	}

	public SelectItem getBuySellFlag() {
		return buySellFlag;
	}

	public void setBuySellFlag(SelectItem buySellFlag) {
		this.buySellFlag = buySellFlag;
	}

	public DateTimeItem getValueDate() {
		return valueDate;
	}

	public void setValueDate(DateTimeItem valueDate) {
		this.valueDate = valueDate;
	}

	public ButtonItem getSaveTradeButton() {
		return saveTradeButton;
	}

	public void setSaveTradeButton(ButtonItem saveTradeButton) {
		this.saveTradeButton = saveTradeButton;
	}
}
