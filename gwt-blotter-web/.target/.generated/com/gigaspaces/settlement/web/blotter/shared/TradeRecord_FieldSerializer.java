package com.gigaspaces.settlement.web.blotter.shared;

@SuppressWarnings("deprecation")
public class TradeRecord_FieldSerializer {
  private static native java.lang.String getAccount(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::account;
  }-*/;
  
  private static native void  setAccount(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::account = value;
  }-*/;
  
  private static native java.lang.Double getAmount(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::amount;
  }-*/;
  
  private static native void  setAmount(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.Double value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::amount = value;
  }-*/;
  
  private static native java.lang.String getBuySellFlag(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::buySellFlag;
  }-*/;
  
  private static native void  setBuySellFlag(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::buySellFlag = value;
  }-*/;
  
  private static native java.lang.String getCounterparty(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::counterparty;
  }-*/;
  
  private static native void  setCounterparty(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::counterparty = value;
  }-*/;
  
  private static native java.lang.String getInstrument(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::instrument;
  }-*/;
  
  private static native void  setInstrument(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::instrument = value;
  }-*/;
  
  private static native java.lang.Boolean getProcessed(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::processed;
  }-*/;
  
  private static native void  setProcessed(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.Boolean value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::processed = value;
  }-*/;
  
  private static native java.lang.String getReference(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::reference;
  }-*/;
  
  private static native void  setReference(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::reference = value;
  }-*/;
  
  private static native java.lang.String getRouting(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::routing;
  }-*/;
  
  private static native void  setRouting(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::routing = value;
  }-*/;
  
  private static native java.util.Date getTradeDate(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::tradeDate;
  }-*/;
  
  private static native void  setTradeDate(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.util.Date value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::tradeDate = value;
  }-*/;
  
  private static native java.lang.String getTradeId(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::tradeId;
  }-*/;
  
  private static native void  setTradeId(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::tradeId = value;
  }-*/;
  
  private static native java.lang.String getTradingParty(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::tradingParty;
  }-*/;
  
  private static native void  setTradingParty(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.lang.String value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::tradingParty = value;
  }-*/;
  
  private static native java.util.Date getValueDate(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) /*-{
    return instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::valueDate;
  }-*/;
  
  private static native void  setValueDate(com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance, java.util.Date value) /*-{
    instance.@com.gigaspaces.settlement.web.blotter.shared.TradeRecord::valueDate = value;
  }-*/;
  
  public static void deserialize(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader, com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) throws com.google.gwt.user.client.rpc.SerializationException{
    setAccount(instance, streamReader.readString());
    setAmount(instance, (java.lang.Double) streamReader.readObject());
    setBuySellFlag(instance, streamReader.readString());
    setCounterparty(instance, streamReader.readString());
    setInstrument(instance, streamReader.readString());
    setProcessed(instance, (java.lang.Boolean) streamReader.readObject());
    setReference(instance, streamReader.readString());
    setRouting(instance, streamReader.readString());
    setTradeDate(instance, (java.util.Date) streamReader.readObject());
    setTradeId(instance, streamReader.readString());
    setTradingParty(instance, streamReader.readString());
    setValueDate(instance, (java.util.Date) streamReader.readObject());
    
  }
  
  public static native com.gigaspaces.settlement.web.blotter.shared.TradeRecord instantiate(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader) throws com.google.gwt.user.client.rpc.SerializationException/*-{
    return @com.gigaspaces.settlement.web.blotter.shared.TradeRecord::new()();
  }-*/;
  
  public static void serialize(com.google.gwt.user.client.rpc.SerializationStreamWriter streamWriter, com.gigaspaces.settlement.web.blotter.shared.TradeRecord instance) throws com.google.gwt.user.client.rpc.SerializationException {
    streamWriter.writeString(getAccount(instance));
    streamWriter.writeObject(getAmount(instance));
    streamWriter.writeString(getBuySellFlag(instance));
    streamWriter.writeString(getCounterparty(instance));
    streamWriter.writeString(getInstrument(instance));
    streamWriter.writeObject(getProcessed(instance));
    streamWriter.writeString(getReference(instance));
    streamWriter.writeString(getRouting(instance));
    streamWriter.writeObject(getTradeDate(instance));
    streamWriter.writeString(getTradeId(instance));
    streamWriter.writeString(getTradingParty(instance));
    streamWriter.writeObject(getValueDate(instance));
    
  }
  
}
