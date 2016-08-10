package com.allen_sauer.gwt.log.client;

import java.util.Date;
import com.allen_sauer.gwt.log.client.util.LogUtil;
import com.google.gwt.core.client.Duration;

public class LogMessageFormatterImpl implements com.allen_sauer.gwt.log.client.LogMessageFormatter {
  
  private double BIG_BANG = Duration.currentTimeMillis();
  
  public String format(String logLevelText, String message) {
    if (message == null) {
      message = "<null message>";
    }
    return ""
     + ""
     + (LogUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss,SSS")) // "%d"
     + " ["
     + (LogUtil.padRight(logLevelText, 5)) // "%-5p"
     + "] "
     + (message) // "%m"
     + ""
     + ("\n") // "%n"
     + "";
  }
  
}
