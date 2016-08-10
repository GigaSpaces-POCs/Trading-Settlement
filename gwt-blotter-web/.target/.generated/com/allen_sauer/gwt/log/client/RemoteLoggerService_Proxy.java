package com.allen_sauer.gwt.log.client;

import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;

public class RemoteLoggerService_Proxy extends RemoteServiceProxy implements com.allen_sauer.gwt.log.client.RemoteLoggerServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "com.allen_sauer.gwt.log.client.RemoteLoggerService";
  private static final String SERIALIZATION_POLICY ="D34FD49D16D766959AF7C96A28A7F508";
  private static final com.allen_sauer.gwt.log.client.RemoteLoggerService_TypeSerializer SERIALIZER = new com.allen_sauer.gwt.log.client.RemoteLoggerService_TypeSerializer();
  
  public RemoteLoggerService_Proxy() {
    super(GWT.getModuleBaseURL(),
      null, 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void debug(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable ex, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.debug", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("debug");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(ex);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.debug", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.debug", requestId, payload, callback);
    } catch (SerializationException ex0) {
      callback.onFailure(ex0);
    }
  }
  
  public void diagnostic(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable throwable, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.diagnostic", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("diagnostic");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(throwable);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.diagnostic", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.diagnostic", requestId, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void error(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable ex, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.error", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("error");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(ex);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.error", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.error", requestId, payload, callback);
    } catch (SerializationException ex0) {
      callback.onFailure(ex0);
    }
  }
  
  public void fatal(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable ex, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.fatal", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("fatal");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(ex);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.fatal", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.fatal", requestId, payload, callback);
    } catch (SerializationException ex0) {
      callback.onFailure(ex0);
    }
  }
  
  public void info(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable ex, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.info", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("info");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(ex);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.info", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.info", requestId, payload, callback);
    } catch (SerializationException ex0) {
      callback.onFailure(ex0);
    }
  }
  
  public void trace(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable ex, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.trace", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("trace");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(ex);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.trace", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.trace", requestId, payload, callback);
    } catch (SerializationException ex0) {
      callback.onFailure(ex0);
    }
  }
  
  public void warn(java.lang.String message, com.allen_sauer.gwt.log.client.WrappedClientThrowable ex, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.warn", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("warn");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("com.allen_sauer.gwt.log.client.WrappedClientThrowable/2731004232");
      streamWriter.writeString(message);
      streamWriter.writeObject(ex);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("RemoteLoggerService_Proxy.warn", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "RemoteLoggerService_Proxy.warn", requestId, payload, callback);
    } catch (SerializationException ex0) {
      callback.onFailure(ex0);
    }
  }
}
