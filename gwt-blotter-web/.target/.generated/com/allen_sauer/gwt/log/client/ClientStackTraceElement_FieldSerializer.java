package com.allen_sauer.gwt.log.client;

@SuppressWarnings("deprecation")
public class ClientStackTraceElement_FieldSerializer {
  private static native java.lang.String getDeclaringClass(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::declaringClass;
  }-*/;
  
  private static native void  setDeclaringClass(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance, java.lang.String value) /*-{
    instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::declaringClass = value;
  }-*/;
  
  private static native java.lang.String getFileName(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::fileName;
  }-*/;
  
  private static native void  setFileName(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance, java.lang.String value) /*-{
    instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::fileName = value;
  }-*/;
  
  private static native int getLineNumber(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::lineNumber;
  }-*/;
  
  private static native void  setLineNumber(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance, int value) /*-{
    instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::lineNumber = value;
  }-*/;
  
  private static native java.lang.String getMethodName(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance) /*-{
    return instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::methodName;
  }-*/;
  
  private static native void  setMethodName(com.allen_sauer.gwt.log.client.ClientStackTraceElement instance, java.lang.String value) /*-{
    instance.@com.allen_sauer.gwt.log.client.ClientStackTraceElement::methodName = value;
  }-*/;
  
  public static void deserialize(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader, com.allen_sauer.gwt.log.client.ClientStackTraceElement instance) throws com.google.gwt.user.client.rpc.SerializationException{
    setDeclaringClass(instance, streamReader.readString());
    setFileName(instance, streamReader.readString());
    setLineNumber(instance, streamReader.readInt());
    setMethodName(instance, streamReader.readString());
    
  }
  
  public static native com.allen_sauer.gwt.log.client.ClientStackTraceElement instantiate(com.google.gwt.user.client.rpc.SerializationStreamReader streamReader) throws com.google.gwt.user.client.rpc.SerializationException/*-{
    return @com.allen_sauer.gwt.log.client.ClientStackTraceElement::new()();
  }-*/;
  
  public static void serialize(com.google.gwt.user.client.rpc.SerializationStreamWriter streamWriter, com.allen_sauer.gwt.log.client.ClientStackTraceElement instance) throws com.google.gwt.user.client.rpc.SerializationException {
    streamWriter.writeString(getDeclaringClass(instance));
    streamWriter.writeString(getFileName(instance));
    streamWriter.writeInt(getLineNumber(instance));
    streamWriter.writeString(getMethodName(instance));
    
  }
  
}
