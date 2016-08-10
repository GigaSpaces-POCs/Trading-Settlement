package com.gigaspaces.settlement;

import javax.annotation.PostConstruct;

import org.openspaces.remoting.ExecutorProxy;
import org.openspaces.remoting.RemotingService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigaspaces.settlement.feeder.OptionTradeFeeder;
import com.gigaspaces.settlement.model.OptionTrade;

@RemotingService
public class OptionTradeFeederServiceImpl implements OptionTradeFeederService {
	
	@ExecutorProxy(gigaSpace = "gigaSpace")
	private OptionSettlementAppService optionSettlementAppService;
	
	@Autowired
	private OptionTradeFeeder optionTradeFeeder;
	
	public void setOptionTradeFeeder(OptionTradeFeeder optionTradeFeeder) {
		this.optionTradeFeeder = optionTradeFeeder;
	}

	public OptionTradeFeeder getOptionTradeFeeder() {
		return optionTradeFeeder;
	}

	public void setSettlementAppService(OptionSettlementAppService optionSettlementAppService) {
		this.optionSettlementAppService = optionSettlementAppService;
	}

	public OptionSettlementAppService getOptionSettlementAppService() {
		return optionSettlementAppService;
	}

	@Override
	public void generateOptionTrades(int numberOfOptionTrades) throws Exception {
		for (int i = 0; i < numberOfOptionTrades; i++) {
			generateOptionTrade();
		}
	}

	@Override
	public OptionTrade generateOptionTrade() throws Exception {
		OptionTrade optionTrade = OptionTrade.generateRandomOptionTrade();
		optionTrade = getOptionSettlementAppService().saveOptionTrade(optionTrade);
		
		return optionTrade;
	}

	@Override
	public void setOptionTradeFeederInterval(int milliseconds) throws Exception {
		optionTradeFeeder.setDefaultDelay(milliseconds);
	}

	@Override
	public void stopFeeder() throws Exception {
		getOptionTradeFeeder().stopFeeder();
	}
	
	@PostConstruct
	public void startFeeder () {
		try {
			startFeeder(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startFeeder(int intervalInMilliseconds) throws Exception {
		try {
			getOptionTradeFeeder().startFeeder(intervalInMilliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
