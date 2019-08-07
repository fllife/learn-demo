package com.mxm.java.learn_demo.tools.utils;

import com.mxm.java.learn_demo.tools.enums.ErrorCode;
import com.mxm.java.learn_demo.tools.exception.ParameterException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/***
 *  Created with IntelliJ IDEA.
 *  User:  xiamo
 *  Date:  2017-11-13
 *  Time: 14:02
 *  Description: MD5工具类
 **/
public class MD5Utils {
  private final static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

  private MD5Utils() {
  }

  private static String byteArrayToHexString(byte b[]) {
    StringBuilder resultSb = new StringBuilder();
    for (byte aB : b) {
      resultSb.append(byteToHexString(aB));
    }

    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0) {
      n += 256;
    }
    int d1 = n / 16;
    int d2 = n % 16;
    return HEX_DIGITS[d1] + HEX_DIGITS[d2];
  }

  public static String md5Encode(String origin, String charsetname) {
    String resultString = null;
    try {
      resultString = origin;
      MessageDigest md = MessageDigest.getInstance("MD5");
      if (charsetname == null || "".equals(charsetname)) {
        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
      } else {
        resultString = byteArrayToHexString(md.digest(resultString
            .getBytes(charsetname)));
      }
    } catch (Exception exception) {
      logger.error("MD5签名失败:", exception);
    }
    return resultString;
  }

  public static String md5Encode(String origin) {
    return md5Encode(origin, "UTF-8");
  }

  /**
   * 加salt方式
   * @author maxianming
   * @date 2019/3/5 13:37
   */
  public static String md5EncodeBySalt(String origin, String salt) {
      if (StringUtils.isEmpty(origin) || StringUtils.isEmpty(salt)) {
          throw  new ParameterException(ErrorCode.INVALIAD_PARAM);
      }
      return md5Encode(origin + salt, "UTF-8");
  }

  private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
      "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
