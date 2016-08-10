package com.gigaspaces.settlement;

import com.gigaspaces.settlement.model.OptionTrade;

public interface OptionTradeFeederService extends GenericFeederService {
	void generateOptionTrades(int numberOfOptionTrades) throws Exception;
	void setOptionTradeFeederInterval(int milliseconds) throws Exception;
	OptionTrade generateOptionTrade() throws Exception;
}
