package com.allen_sauer.gwt.log.client;

@SuppressWarnings("deprecation")
public class WrappedClientThrowable_FieldSerializer {
  private static native com.allen_sauer.gwt.log.client.ClientStackTraceElement[] getClientStackTrace(com.allen_sauer.gwt.log.client.WrappedClientThrowable instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.WrappedClientThrowable::clientStackTrace;
  }-*/;
  
  private static native void  setClientStackTrace(com.allen_sauer.gwt.log.client.WrappedClientThrowable instance, com.allen_sauer.gwt.log.client.ClientStackTraceElement[] value) /*-{
    instance.@com.allen_sauer.gwt.log.client.WrappedClientThrowable::clientStackTrace = value;
  }-*/;
  
  private static native java.lang.String getMessage(com.allen_sauer.gwt.log.client.WrappedClientThrowable instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.WrappedClientThrowable::message;
  }-*/;
  
  private static native void  setMessage(com.allen_sauer.gwt.log.client.WrappedClientThrowable instance, java.lang.String value) /*-{
    instance.@com.allen_sauer.gwt.log.client.WrappedClientThrowable::message = value;
  }-*/;
  
  private static native java.lang.String getOriginalClassName(com.allen_sauer.gwt.log.client.WrappedClientThrowable instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.WrappedClientThrowable::originalClassName;
  }-*/;
  
  private static native void  setOriginalClassName(com.allen_sauer.gwt.log.client.WrappedClientThrowable instance, java.lang.String value) /*-{
    instance.@com.allen_sauer.gwt.log.client.WrappedClientThrowable::originalClassName = value;
  }-*/;
  
  public static void deserialize(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader, com.allen_sauer.gwt.log.client.WrappedClientThrowable instance) throws com.google.gwt.user.client.rpc.SerializationException{
    setClientStackTrace(instance, (com.allen_sauer.gwt.log.client.ClientStackTraceElement[]) streamReader.readObject());
    setMessage(instance, streamReader.readString());
    setOriginalClassName(instance, streamReader.readString());
    
  }
  
  public static native com.allen_sauer.gwt.log.client.WrappedClientThrowable instantiate(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader) throws com.google.gwt.user.client.rpc.SerializationException/*-{
    return @com.allen_sauer.gwt.log.client.WrappedClientThrowable::new()();
  }-*/;
  
  public static void serialize(com.google.gwt.user.client.rpc.SerializationStreamWriter streamWriter, com.allen_sauer.gwt.log.client.WrappedClientThrowable instance) throws com.google.gwt.user.client.rpc.SerializationException {
    streamWriter.writeObject(getClientStackTrace(instance));
    streamWriter.writeString(getMessage(instance));
    streamWriter.writeString(getOriginalClassName(instance));
    
  }
  
}
