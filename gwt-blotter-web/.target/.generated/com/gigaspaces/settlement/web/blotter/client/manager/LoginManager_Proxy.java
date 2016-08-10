package com.gigaspaces.settlement.web.blotter.client.manager;

import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;

public class LoginManager_Proxy extends RemoteServiceProxy implements com.gigaspaces.settlement.web.blotter.client.manager.LoginManagerAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "com.gigaspaces.settlement.web.blotter.client.manager.LoginManager";
  private static final String SERIALIZATION_POLICY ="17BD96CB629BF0AEBC33B9A448582A38";
  private static final com.gigaspaces.settlement.web.blotter.client.manager.LoginManager_TypeSerializer SERIALIZER = new com.gigaspaces.settlement.web.blotter.client.manager.LoginManager_TypeSerializer();
  
  public LoginManager_Proxy() {
    super(GWT.getModuleBaseURL(),
      "loginManager", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void login(java.lang.String user, java.lang.String password, com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("LoginManager_Proxy.login", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("login");
      streamWriter.writeInt(2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(user);
      streamWriter.writeString(password);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("LoginManager_Proxy.login", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "LoginManager_Proxy.login", requestId, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
  
  public void logoff(com.google.gwt.user.client.rpc.AsyncCallback callback) {
    int requestId = getNextRequestId();
    boolean toss = isStatsAvailable() && stats(timeStat("LoginManager_Proxy.logoff", requestId, "begin"));
    SerializationStreamWriter streamWriter = createStreamWriter();
    // createStreamWriter() prepared the stream
    try {
      streamWriter.writeString(REMOTE_SERVICE_INTERFACE_NAME);
      streamWriter.writeString("logoff");
      streamWriter.writeInt(0);
      String payload = streamWriter.toString();
      toss = isStatsAvailable() && stats(timeStat("LoginManager_Proxy.logoff", requestId, "requestSerialized"));
      doInvoke(ResponseReader.VOID, "LoginManager_Proxy.logoff", requestId, payload, callback);
    } catch (SerializationException ex) {
      callback.onFailure(ex);
    }
  }
}
