package com.gigaspaces.settlement.web.blotter.shared;

@SuppressWarnings("deprecation")
public class MatchedDealRecord_Array_Rank_1_FieldSerializer {
  public static void deserialize(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader, com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord[] instance) throws com.google.gwt.user.client.rpc.SerializationException{
    com.google.gwt.user.client.rpc.core.java.lang.Object_Array_CustomFieldSerializer.deserialize(streamReader, instance);
  }
  
  public static com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord[] instantiate(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader) throws com.google.gwt.user.client.rpc.SerializationException{
    int rank = streamReader.readInt();
    return new com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord[rank];
  }
  
  public static void serialize(com.google.gwt.user.client.rpc.SerializationStreamWriter streamWriter, com.gigaspaces.settlement.web.blotter.shared.MatchedDealRecord[] instance) throws com.google.gwt.user.client.rpc.SerializationException {
    com.google.gwt.user.client.rpc.core.java.lang.Object_Array_CustomFieldSerializer.serialize(streamWriter, instance);
  }
  
}
