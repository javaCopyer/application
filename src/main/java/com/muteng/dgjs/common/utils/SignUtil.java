package com.muteng.dgjs.common.utils;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;


public final class SignUtil implements Serializable {

  private static final long serialVersionUID = 4249197347473419100L;


  private SignUtil() {

  }


  public static String generateMD5(Map<String, String> map, String userKey) throws Exception {
    return generateMD5(map, userKey, "GBK");
  }


  public static String generateMD5(Map<String, String> map, String userKey, String charset) throws Exception {
    String sign = null;
    List<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    String params = "";
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = map.get(key);
      if (value == null || value.length() == 0 || key.equals("sign")) {
        map.remove(key);
        continue;
      }
      params = params + key + "=" + value;
      if (i != keys.size() - 1)
        params += "&";
    }
    params = params + "&key=" + userKey;
    sign = DigestUtils.md5Hex(params.getBytes(charset));
    return sign;
  }


  public static boolean verifyRSA(Map<String, String> map, String sign) throws Exception {
    return verifyRSA(map, sign, "GBK");
  }


  public static boolean verifyRSA(Map<String, String> map, String sign, String charset) throws Exception {
    List<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    String params = "";
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = map.get(key);
      if (value == null || value.length() == 0 || key.equals("sign") || key.equals("sign_type")) {
        map.remove(key);
        continue;
      }
      params = params + key + "=" + value;
      if (i != keys.size() - 1)
        params += "&";
    }
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    String aliPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    byte[] encodedKey =new org.apache.commons.codec.binary.Base64().decode(aliPublicKey);
    PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    Signature signature = java.security.Signature.getInstance("SHA1WithRSA");
    signature.initVerify(pubKey);
    signature.update(params.getBytes(charset));
    return signature.verify(new org.apache.commons.codec.binary.Base64().decode(sign));
  }


}