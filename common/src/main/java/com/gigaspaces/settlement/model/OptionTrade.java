package com.gigaspaces.settlement.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

@SpaceClass
public class OptionTrade implements Serializable {
	private static String[] entities = new String[] {
		"cust1","cust2","cust3","bank1","bank2","bank3"};
	
	private static String[] instruments = new String[] {
		"SPY","DIA","AAPL"
	};
	
	private static String[] flags = new String[] { "B","S" };
	
	private static String[] accounts = new String[] {
		"account1","acct2","US acct","GB acct"};
	
	private static String[] optionTypes = new String[] {"C", "P"};
	
	private static Date optionExpirationDateDefault = new Date(System.currentTimeMillis());
	
	private static Float[] strikePrices = new Float[] {11.0f, 12.0f};
	
	static {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.add(Calendar.YEAR, +2);
		currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
		currentCalendar.set(Calendar.MINUTE, 0);
		currentCalendar.set(Calendar.SECOND, 0);
		currentCalendar.set(Calendar.MILLISECOND, 0);
		optionExpirationDateDefault = new Date(currentCalendar.getTimeInMillis());
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4564357320248339430L;
	private static int tradeNumber = 1;
	private String tradeId;
	private String reference;
	private String tradingParty;
	private String counterparty;
	private String instrument;
	private String optionType;
	private Float strikePrice;
	private Date optionExpirationDate;
	private Boolean processed = false;
	private String buySellFlag;
	private Double amount;
	private String account;
	private Date valueDate;
	private Date tradeDate;
	private String routing;
	private Boolean matched = false;

	@SpaceId(autoGenerate = true)
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

	@SpaceRouting
	public String getRouting() {
		if (tradingParty == null) {
			return null;
		}
		String[] entities = new String[] {tradingParty,counterparty};
		Arrays.sort(entities);
		routing = entities[0] + "-" + entities[1];
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

	public static OptionTrade generateRandomOptionTrade() {
		OptionTrade t = new OptionTrade();
		int index = new Random().nextInt(entities.length);
		String tradingParty = entities[index];
		t.setTradingParty(tradingParty);
		
		t.setReference(tradingParty + "-" + tradeNumber++);
		if (tradeNumber % 2 == 0) {
			t.setOptionType(optionTypes[0]);
			t.setStrikePrice(strikePrices[0]);
		} else {
			t.setOptionType(optionTypes[1]);
			t.setStrikePrice(strikePrices[1]);
		}
		t.setOptionExpirationDate(optionExpirationDateDefault);
		
		index = new Random().nextInt(entities.length);
		String counterparty = entities[index];
		
		while (tradingParty.equals(counterparty)) {
			index = new Random().nextInt(entities.length);
			counterparty = entities[index];
			if (!counterparty.equals(tradingParty)) {
				break;
			}
		}
		t.setCounterparty(counterparty);
		t.setInstrument(instruments[new Random().nextInt(instruments.length)]);
		t.setBuySellFlag(flags[new Random().nextInt(flags.length)]);
		t.setAccount(accounts[new Random().nextInt(accounts.length)]);
		t.setTradeDate(new Date());
		// Get a value date in the next 3 days
		Calendar cdr = Calendar.getInstance();
		cdr.add(Calendar.DAY_OF_YEAR, new Random().nextInt(3));
		cdr.set(Calendar.HOUR_OF_DAY, 0);
		cdr.set(Calendar.MINUTE, 0);
		cdr.set(Calendar.SECOND, 0);
		t.setValueDate(cdr.getTime());
		// Get an amount
		double amount = trunc((new Random().nextDouble()) * 1000,2);
		t.setAmount(amount);
		return t;
	}
	
	public Date getOptionExpirationDate() {
		return optionExpirationDate;
	}

	public void setOptionExpirationDate(Date optionExpirationDate) {
		this.optionExpirationDate = optionExpirationDate;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public Float getStrikePrice() {
		return strikePrice;
	}

	public void setStrikePrice(Float strikePrice) {
		this.strikePrice = strikePrice;
	}

	public static void main(String[] args) {
		OptionTrade.generateRandomOptionTrade();
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	public Boolean getMatched() {
		return matched;
	}
	
	private static double trunc(double d,int n) {
		int dec = (int) Math.pow(10, n);
		d = (Math.floor(d * dec) / dec);
		return d;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [tradeId=");
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
		builder.append(", optionExpirationDate=");
		builder.append(optionExpirationDate);
		builder.append(", optionType=");
		builder.append(optionType);
		builder.append(", strikePrice=");
		builder.append(strikePrice);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", routing=");
		builder.append(routing);
		builder.append(", matched=");
		builder.append(matched);
		builder.append("]");
		return builder.toString();
	}
}
