package com.gigaspaces.settlement.web.blotter.server.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.gigaspaces.settlement.SettlementAppService;
import com.gigaspaces.settlement.TradeFeederService;
import com.gigaspaces.settlement.model.MatchedDeal;
import com.gigaspaces.settlement.model.Trade;
import com.gigaspaces.settlement.web.blotter.client.manager.DealManager;
import com.gigaspaces.settlement.web.blotter.server.ApplicationContextProvider;
import com.gigaspaces.settlement.web.blotter.shared.Constants;
import com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord;
import com.gigaspaces.settlement.web.blotter.shared.TradeRecord;
import com.gigaspaces.util.GenericComparator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DealManagerImpl extends RemoteServiceServlet implements DealManager {

	private static final long serialVersionUID = 3237826891820584247L;
	private static final Log LOG = LogFactory.getLog(DealManagerImpl.class);

	private SettlementAppService settlementAppServiceDistributed;
	private SettlementAppService settlementAppServiceRouted;
	private TradeFeederService tradeFeederService;

	public DealManagerImpl() {

		setSettlementAppServiceRouted((SettlementAppService) ApplicationContextProvider
				.getContext().getBean("settlementAppServiceRouted"));
		setSettlementAppServiceDistributed((SettlementAppService) ApplicationContextProvider
				.getContext().getBean("settlementAppServiceDistributed"));
		setTradeFeederService((TradeFeederService) ApplicationContextProvider
				.getContext().getBean("tradeFeederService"));
	}

	/**
	 * Retrieve trades from remote service. Convert into TradeRecord[]
	 */
	@Override
	public List<TradeRecord> getCounterpartyTrades(String counterparty)
			throws Exception {
		HttpSession session = getThreadLocalRequest().getSession();
		String user = (String) session.getAttribute(Constants.USER_KEY);
		String entity = StringUtils.substringAfter(user, "@");

		LOG.info("retrieving counterparty trades for entity: " + entity
				+ " and counterparty " + counterparty);
		List<TradeRecord> records = new ArrayList<TradeRecord>();
		List<Trade> trades = getSettlementAppServiceDistributed()
				.getCounterpartyTrades(entity, counterparty);
		for (Trade trade : trades) {
			TradeRecord tradeRecord = new TradeRecord();
			BeanUtils.copyProperties(trade, tradeRecord);
			records.add(tradeRecord);
		}
		LOG.info("retrieved " + trades.size() + " counterparty trades");
		
		Collections.sort(records, new GenericComparator("tradeDate"));

		return records;
	}

	/**
	 * Retrieve trades from remote service. Method allows the user to filter by
	 * counterparty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TradeRecord> getTrades(String counterparty) throws Exception {
		HttpSession session = getThreadLocalRequest().getSession();
		String user = (String) session.getAttribute(Constants.USER_KEY);
		String entity = StringUtils.substringAfter(user, "@");

		LOG.info("retrieving counterparty trades for entity: " + entity
				+ " and counterparty " + counterparty);
		List<TradeRecord> records = new ArrayList<TradeRecord>();
		List<Trade> trades = getSettlementAppServiceDistributed().getTrades(entity,
				counterparty);
		for (Trade trade : trades) {
			TradeRecord tradeRecord = new TradeRecord();
			BeanUtils.copyProperties(trade, tradeRecord);
			records.add(tradeRecord);
		}
		LOG.info("retrieved " + trades.size() + " trades");
		Collections.sort(records, new GenericComparator("tradeDate"));
		return records;
	}

	@Override
	public List<MatchedDealRecord> getMatchedDeals(String counterparty)
			throws Exception {
		LOG.info("retrieving matched deals for counterparty: " + counterparty);
		HttpSession session = getThreadLocalRequest().getSession();
		String user = (String) session.getAttribute(Constants.USER_KEY);
		String entity = StringUtils.substringAfter(user, "@");

		List<MatchedDealRecord> records = new ArrayList<MatchedDealRecord>();
		List<MatchedDeal> matchedDeals = new ArrayList<MatchedDeal>();
		try {
			if (counterparty != null) {
				matchedDeals = getSettlementAppServiceDistributed().getMatchedDeals(entity, counterparty);
			} else {
				matchedDeals = getSettlementAppServiceDistributed().getMatchedDeals(entity);
			}

			LOG.info("retrieved " + matchedDeals.size() + " deals");
			for (MatchedDeal matchedDeal : matchedDeals) {
				MatchedDealRecord record = new MatchedDealRecord();
				BeanUtils.copyProperties(matchedDeal, record);
				records.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(records, new GenericComparator("matchDate"));
		return records;
	}

	@Override
	public TradeRecord saveTrade(TradeRecord tradeRecord) throws Exception {
		LOG.info("Saving tradeRecord: " + tradeRecord);
		Trade trade = new Trade();
		BeanUtils.copyProperties(tradeRecord, trade);
		LOG.info("Saving trade: " + trade);
		trade = getSettlementAppServiceRouted().saveTrade(trade);
		tradeRecord.setTradeId(trade.getTradeId());
		return tradeRecord;
	}
	

	@Override
	public void startFeeder(int refreshInterval) throws Exception {
		getTradeFeederService().startFeeder(refreshInterval);
	}
	
	@Override
	public void stopFeeder() throws Exception {
		getTradeFeederService().stopFeeder();
	}

	@Override
	public void clearUnmatchedTrades() throws Exception {
		getSettlementAppServiceDistributed().clearUnmatchedTrades();
	}

	public void setSettlementAppServiceRouted(
			SettlementAppService settlementAppServiceRouted) {
		this.settlementAppServiceRouted = settlementAppServiceRouted;
	}

	public SettlementAppService getSettlementAppServiceRouted() {
		return settlementAppServiceRouted;
	}

	public void setSettlementAppServiceDistributed(
			SettlementAppService settlementAppServiceDistributed) {
		this.settlementAppServiceDistributed = settlementAppServiceDistributed;
	}

	public SettlementAppService getSettlementAppServiceDistributed() {
		return settlementAppServiceDistributed;
	}

	public void setTradeFeederService(TradeFeederService tradeFeederService) {
		this.tradeFeederService = tradeFeederService;
	}

	public TradeFeederService getTradeFeederService() {
		return tradeFeederService;
	}
}
