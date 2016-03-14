package com.gigaspaces.settlement.feeder.jmx;

public interface ITradeFeederJMXMonitor {

	void generateTrades(int numberOfTrades) throws Exception;
	
	void setTradeFeederInterval(int milliseconds) throws Exception;
}
