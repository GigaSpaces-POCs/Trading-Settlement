package com.gigaspaces.settlement.web.blotter.client.manager;

import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;

public class DealManager_Proxy extends RemoteServiceProxy implements com.gigaspaces.settlement.web.blotter.client.manager.DealManagerAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "com.gigaspaces.settlement.web.blotter.client.manager.DealManager";
  private static final String SERIALIZATION_POLICY ="28381CF42D599C20D6BCF9569E7297AB";
  private static final com.gigaspaces.settlement.web.blotter.client.manager.DealManager_TypeSerializer SERIALIZER = new com.gigaspaces.settlement.web.blotter.client.manager.DealManager_TypeSerializer();
  
  public DealManager_Proxy() {
    super(GWT.getModuleBaseURL(),
      "dealManager", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void clearUnmatchedTrades(com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.clearUnmatchedTrades", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("clearUnmatchedTrades");
      streamWriter.writeInt(0);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.clearUnmatchedTrades", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "DealManager_Proxy.clearUnmatchedTrades", requestId, payload, asyncCallback);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getCounterpartyTrades(java.lang.String entity, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.getCounterpartyTrades", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("getCounterpartyTrades");
      streamWriter.writeInt(1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(entity);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.getCounterpartyTrades", requestId, "requestSerialized"));
      doInvoke(ResponseReader.OBJECT, "DealManager_Proxy.getCounterpartyTrades", requestId, payload, asyncCallback);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getMatchedDeals(java.lang.String customerName, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.getMatchedDeals", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("getMatchedDeals");
      streamWriter.writeInt(1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(customerName);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.getMatchedDeals", requestId, "requestSerialized"));
      doInvoke(ResponseReader.OBJECT, "DealManager_Proxy.getMatchedDeals", requestId, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void getTrades(java.lang.String entity, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.getTrades", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("getTrades");
      streamWriter.writeInt(1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(entity);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.getTrades", requestId, "requestSerialized"));
      doInvoke(ResponseReader.OBJECT, "DealManager_Proxy.getTrades", requestId, payload, asyncCallback);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void saveTrade(com.gigaspaces.settlement.web.blotter.shared.TradeRecord tradeRecord, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.saveTrade", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("saveTrade");
      streamWriter.writeInt(1);
      streamWriter.writeString("com.gigaspaces.settlement.web.blotter.shared.TradeRecord/769549644");
      streamWriter.writeObject(tradeRecord);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.saveTrade", requestId, "requestSerialized"));
      doInvoke(ResponseReader.OBJECT, "DealManager_Proxy.saveTrade", requestId, payload, asyncCallback);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void startFeeder(int refreshInterval, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.startFeeder", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("startFeeder");
      streamWriter.writeInt(1);
      streamWriter.writeString("I");
      streamWriter.writeInt(refreshInterval);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.startFeeder", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "DealManager_Proxy.startFeeder", requestId, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void stopFeeder(com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.stopFeeder", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("stopFeeder");
      streamWriter.writeInt(0);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("DealManager_Proxy.stopFeeder", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "DealManager_Proxy.stopFeeder", requestId, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
}
