package com.example.guardianvault.util;

import org.apache.logging.log4j.util.Base64Util;

public class CommonUtils {

  public static String passwordEncoder(String password){
    return Base64Util.encode(password);
  }

}
