package com.muteng.dgjs.common.utils;
import java.security.Key;
import java.security.MessageDigest;

//老版本
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordED {
	private Cipher ciper_;
	private Key key_ ;
	private byte[] keys = {0x05, 0x27, 0x73, 0x56, 0x44, 0x11, 0x32, 0x54 };
	private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5",
	        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	public PasswordED() 
	{
		try{
		ciper_ = Cipher.getInstance("DES/ecb/PKCS5Padding");
		key_ = new SecretKeySpec(keys, "DES");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//加密
	public String encPassword( String password ){
		byte[] afterDesEnc = null;
		String strRet = "";
		
		afterDesEnc = doEncByDes( password );
		strRet = doEncByBase( afterDesEnc );
		
		return strRet;
	}
	
	//解密
	public String decPassword( String encryptedPassword ){
		byte[] afterBaseDec = null;
		String strRet = "";
		
		afterBaseDec = doDecByBase( encryptedPassword );
		strRet = doDecByDes( afterBaseDec );
		
		return strRet;
		
	}
	
	private byte[] doEncByDes( String strPlain )
	{
		byte encrypted[] = {0};
		try {
			ciper_.init(Cipher.ENCRYPT_MODE, key_ );
			byte input[] = strPlain.getBytes();
			encrypted = ciper_.doFinal(input);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return encrypted;	
	}
		
	
	private String doDecByDes( byte[] encbytes )
	{
		String strDec = "";
		try {
			ciper_.init(Cipher.DECRYPT_MODE, key_);
			byte output[] = ciper_.doFinal( encbytes );
			strDec = new String(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDec;
	}	
	
	private String doEncByBase( byte[] in ){
		String strRet = "";
//		BASE64Encoder en = new BASE64Encoder();
//		strRet = en.encode( in );
		
		strRet = Base64.getEncoder().encodeToString(in);
		return strRet;
	}
	
	private byte[] doDecByBase( String in ){
		if (in == null)
			return null;
//		BASE64Decoder decoder = new BASE64Decoder();
		try {
			
//			byte[] b = decoder.decodeBuffer( in );
			byte[] b = Base64.getDecoder().decode(in);
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * MD5加密(小写)
	 */
	public String getMd5OfStr(String Str) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Str.getBytes());
            resultString = byteArrayToHexString(md.digest());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
	
	public String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    /**
     * 将字节转换为字符串.
     * @param b the byte.
     * @return the hex string.
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }
    

    
    /**
	 * beas64 加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String getBase64(String str)throws Exception {  
		String asB64 = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
		return asB64;
    }  
  
	/**
	 * base64 解密
	 * @param s
	 * @return
	 */
    public String getFromBase64(String s) {  
    	byte[] asBytes = Base64.getDecoder().decode(s); 
    	String resultStr = new String(asBytes);
    	return resultStr;
    }  
	public static void main(String[] args){
		PasswordED ed = new PasswordED();
		String str = ed.encPassword("asd");
		String md5 = ed.decPassword("QpdI8UgOy9LILYMFWiu/wztla0+oKKSMlHuMHFLWQ7nILYMFWiu/w8fyY5ZbsmYhMsGVhyZlAkRLLyLV3aBlcZeWAP6HjlMXTRpd5mo+RKnes9yWgIUbyS6Suwy7z4U7PxesuUpqNIgfs1tKLkmJu44DJs/xZgw2");
		
		System.out.println("str="+str);
		System.out.println("md5="+md5);
//		System.out.println(PasswordED.MD5("lanlingzhifu"));
//		System.out.println(PasswordED.getMd5OfStr("aaaaaaaaaa"));
	
		
//		String str = "{\"resp\" : {\"respCode\" : \"000000\",\"callBack\" : {\"callId\" : \"api1234059445aDbbJxIdbT\",\"createTime\" : \"20150829193453\"}}}";
		

	}
}
