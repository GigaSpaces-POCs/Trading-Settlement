package com.gigaspaces.settlement.feeder.jmx;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigaspaces.settlement.TradeFeederService;

public class TradeFeederJMXMonitor implements ITradeFeederJMXMonitor,DynamicMBean ,InitializingBean,DisposableBean{
	
	@Autowired
	private TradeFeederService tradeFeederService;
	private MBeanServer mbs;
	private ObjectName tradeFeederMBeanName;	

	public void setTradeFeederService(TradeFeederService tradeFeederService) {
		this.tradeFeederService = tradeFeederService;
	}

	public TradeFeederService getTradeFeederService() {
		return tradeFeederService;
	}

	public void setTradeFeederInterval(int milliseconds) throws Exception {
		tradeFeederService.setTradeFeederInterval(milliseconds);
	}
	
	public void generateTrades(int numberOfTrades) throws Exception {
		System.out.println("Generating Trades: " + numberOfTrades);
		tradeFeederService.generateTrades(numberOfTrades);
	}
	
	@Override
	public void destroy() throws Exception {
		mbs.unregisterMBean(tradeFeederMBeanName);		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		mbs = ManagementFactory.getPlatformMBeanServer();
		// Construct the ObjectName for the MBean we will register
		tradeFeederMBeanName= new ObjectName(TradeFeederJMXMonitor.class.getPackage().getName()+ ":type=TradeFeederJMXMonitor");
		mbs.registerMBean(this,tradeFeederMBeanName);
	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException,
			ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
//		MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[1];

		Method generateTrades = null;
//		Method getObjectMethod = null;

		try {
			generateTrades = TradeFeederJMXMonitor.class.getMethod("generateTrades", new Class[] {Integer.TYPE});
//			getObjectMethod = GigaSpacesClientCacheJMXAccessor.class.getMethod("getObject");

		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		MBeanOperationInfo[] operators = new MBeanOperationInfo[] {new MBeanOperationInfo("Generate Trades", generateTrades)};
//			attributes[0] = new MBeanAttributeInfo("GenerateTrades", "GenerateTrades",generateTrades, null);
//			attributes[1] = new MBeanAttributeInfo("Object", "Object",getObjectMethod, null);

		MBeanInfo bi = new MBeanInfo("TradeFeederJMXMonitor", "TradeFeederJMXMonitor Desc",null, null, operators, null);
		return bi;
	}

}
