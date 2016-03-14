package com.gigaspaces.settlement.feeder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigaspaces.settlement.TradeFeederService;
import com.gigaspaces.settlement.model.Trade;

/**
 * A feeder bean starts a scheduled task that writes a new Data objects to the
 * space (in an unprocessed state).
 * 
 * <p>
 * The scheduling uses a thread that periodically writes Data object to the
 * space.
 */
public class TradeFeeder implements DisposableBean {

	private static final Log LOG = LogFactory.getLog(TradeFeeder.class);

	private int defaultDelay = 5000;

	private FeederTask feederTask;
	
	@Autowired
	private TradeFeederService tradeFeederService;

	private Thread feederThread;

	public void setDefaultDelay(int defaultDelay) {
		this.defaultDelay = defaultDelay;
	}
	
	public void startFeeder(int intervalInMilliseconds) {
		if (feederTask == null || !feederThread.isAlive()) {
			feederTask = new FeederTask();
			feederThread = new Thread(feederTask);
			feederThread.start();
		}
		feederTask.setInterval(intervalInMilliseconds);
	}

	public void stopFeeder() {
		feederTask.cancel();
	}

	public class FeederTask implements Runnable {
		private volatile boolean alive = true;
		private volatile int interval = defaultDelay;
		
		public void run() {
			try {
				while (alive) {
					long time = System.currentTimeMillis();
					
					Trade trade = getTradeFeederService().generateTrade();

					LOG.info("--- FEEDER WROTE " + trade.getReference() + " at "	+ time);
					
					Thread.sleep(interval);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void setInterval(int interval) {
			this.interval = interval;
		}

		public void cancel() {
			alive = false;
		}
	}

	@Override
	public void destroy() throws Exception {
		feederTask.cancel();
	}

	public void setTradeFeederService(TradeFeederService tradeFeederService) {
		this.tradeFeederService = tradeFeederService;
	}

	public TradeFeederService getTradeFeederService() {
		return tradeFeederService;
	}
}
