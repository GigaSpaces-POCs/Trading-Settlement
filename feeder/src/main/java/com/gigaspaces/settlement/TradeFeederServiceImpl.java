package com.gigaspaces.settlement;

import org.openspaces.remoting.ExecutorProxy;
import org.openspaces.remoting.RemotingService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigaspaces.settlement.feeder.TradeFeeder;
import com.gigaspaces.settlement.model.Trade;

@RemotingService
public class TradeFeederServiceImpl implements TradeFeederService {
	
	@ExecutorProxy(gigaSpace = "gigaSpace")
	private SettlementAppService settlementAppService;
	
	@Autowired
	private TradeFeeder tradeFeeder;
	
	public void setTradeFeeder(TradeFeeder tradeFeeder) {
		this.tradeFeeder = tradeFeeder;
	}

	public TradeFeeder getTradeFeeder() {
		return tradeFeeder;
	}

	public void setSettlementAppService(SettlementAppService settlementAppService) {
		this.settlementAppService = settlementAppService;
	}

	public SettlementAppService getSettlementAppService() {
		return settlementAppService;
	}

	@Override
	public void generateTrades(int numberOfTrades) throws Exception {
		for (int i = 0; i < numberOfTrades; i++) {
			generateTrade();
		}
	}

	@Override
	public Trade generateTrade() throws Exception {
		Trade trade = Trade.generateRandomTrade();
		trade = getSettlementAppService().saveTrade(trade);
		
		return trade;
	}

	@Override
	public void setTradeFeederInterval(int milliseconds) throws Exception {
		tradeFeeder.setDefaultDelay(milliseconds);
	}

	@Override
	public void stopFeeder() throws Exception {
		getTradeFeeder().stopFeeder();
	}

	@Override
	public void startFeeder(int intervalInMilliseconds) throws Exception {
		try {
			getTradeFeeder().startFeeder(intervalInMilliseconds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
