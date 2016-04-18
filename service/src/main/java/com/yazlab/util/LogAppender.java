package com.yazlab.util;


import org.apache.log4j.DailyRollingFileAppender;

import java.sql.Timestamp;
import java.util.Date;

public class LogAppender extends DailyRollingFileAppender{

  public void setFile(String fileName){
    if (fileName.indexOf("%rnd") >= 0) {
      Date date = new Date();
      fileName = fileName.replaceAll("%rnd", String.valueOf(new Timestamp(date.getTime())));
    }
    super.setFile(fileName);
  }
}
