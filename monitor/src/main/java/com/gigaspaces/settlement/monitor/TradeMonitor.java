package com.gigaspaces.settlement.monitor;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.elastic.config.ManualCapacityScaleConfigurer;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.SpaceInterruptedException;
import org.openspaces.core.util.MemoryUnit;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.gigaspaces.settlement.model.Trade;

/*
 * This class will check for a number of trades
 * When a certain number of trades is exceeded, the components app
 * will scale with 2 more instances
 */
public class TradeMonitor implements InitializingBean, DisposableBean {

	private static Log LOG = LogFactory.getLog(TradeMonitor.class);
	
	static int INITIAL_MEMEORY_CAPACITY_MB=128;
	static int TARGET_MEMEORY_CAPACITY_MB_SCALED_UP=256;
	
	static boolean localMachineDemo = true;
	
	private long defaultDelay = 5000;
	
	private int scaleUpThreshold = 50;
	
	private TradeMonitorTask tradeMonitorTask;

	GigaSpace gigaSpace;

	private Thread monitorThread;

	private Admin admin;

	private String locator;

	public void setDefaultDelay(long defaultDelay) {
		this.defaultDelay = defaultDelay;
	}

	public void setGigaSpace(GigaSpace gigaSpace) {
		this.gigaSpace = gigaSpace;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	@Override
	public void destroy() throws Exception {
		tradeMonitorTask.cancel();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("Starting trade monitor");
		admin = new AdminFactory().addLocator(locator).create();
		admin.getGridServiceAgents().waitForAtLeastOne(10, TimeUnit.SECONDS);
		admin.getElasticServiceManagers().waitForAtLeastOne(10,	TimeUnit.SECONDS);
		
		LOG.info("Retrieved admin instance");
		
		tradeMonitorTask = new TradeMonitorTask();
		monitorThread = new Thread(tradeMonitorTask);
		monitorThread.start();
	}

	public class TradeMonitorTask implements Runnable {

		private volatile boolean alive = true;
		private boolean scaledUp = false;
		ProcessingUnit pu = null;

		@Override
		public void run() {
			try {
				Trade template = new Trade();
				template.setMatched(null);
				template.setProcessed(null);
				
				while (alive) {
					
					// Get the Trade[] from the space
					int tradescount = gigaSpace.count(template);
					LOG.info("Current number of trades in space: " + tradescount);
					
					if (tradescount > scaleUpThreshold && !scaledUp) {
						LOG.info("Scaling up application");
						pu = admin.getProcessingUnits().waitFor("settlement-app-components", 5,TimeUnit.SECONDS);
						pu.scale(new ManualCapacityScaleConfigurer()
				        .memoryCapacity(TARGET_MEMEORY_CAPACITY_MB_SCALED_UP,MemoryUnit.MEGABYTES)
				        .create());
						LOG.info("Scaled up application");
						scaledUp = true;
					} else if (scaledUp && tradescount < scaleUpThreshold) {
						LOG.info("Scaling down application");
						pu = admin.getProcessingUnits().waitFor("settlement-app-components", 5,TimeUnit.SECONDS);
						pu.scale(new ManualCapacityScaleConfigurer()
				        .memoryCapacity(INITIAL_MEMEORY_CAPACITY_MB,MemoryUnit.MEGABYTES)
				        .create());
						LOG.info("Scaled down application");
						scaledUp = false;
					}
					
					Thread.sleep(defaultDelay);

				}
			} catch (SpaceInterruptedException e) {
				// ignore, we are being shutdown
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void cancel() {
			alive = false;
		}
	}

	public long getDefaultDelay() {
		return defaultDelay;
	}

	public void setScaleUpThreshold(int scaleUpThreshold) {
		this.scaleUpThreshold = scaleUpThreshold;
	}

	public int getScaleUpThreshold() {
		return scaleUpThreshold;
	}

}
