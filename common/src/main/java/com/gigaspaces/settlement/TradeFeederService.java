package com.gigaspaces.settlement;

import com.gigaspaces.settlement.model.Trade;

public interface TradeFeederService extends GenericFeederService {
	void generateTrades(int numberOfTrades) throws Exception;
	void setTradeFeederInterval(int milliseconds) throws Exception;
	Trade generateTrade() throws Exception;
}
