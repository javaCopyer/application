package com.muteng.dgjs.common.utils;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class CommonUtil {

	/*-----------------------------------
	 * 去除空格、回车、制表符、换行符
	注：\n 回车(\u000a) 
	\t 水平制表符(\u0009) 
	\s 空格(\u0008) 
	\r 换行(\u000d)
	*/
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 生成随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int nextInt(final int min, final int max) {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}

	/**
	 * 源字符串是否匹配正则表达式
	 * 
	 * @param srcString
	 * @param regex
	 * @return
	 */
	public static boolean isMatching(String srcString, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(srcString);
		return matcher.matches();
	}

	public static String URLEncoder(String str) {
		try {
			String code = URLEncoder.encode(str, "UTF-8");
			return code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int convertCent(double yuan) {
		return (int) Math.round(yuan * 100);
	}

	public static float convertYuan(int cent) {
		return (float) (Math.round(cent) / 100.0);
	}

	public static int convertYuan2(int cent) {
		return (int) (Math.round(cent) / 100);
	}

	// 判断一个字符串是否都为数字
	public static boolean isDigit(String strNum) {
		return isMatching(strNum, "[0-9]{1,}");
	}

	// 获取ip地址
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	
	/**
	 * 获取邮箱账号，不符号邮箱格式返回null;
	 * @param email
	 * @return
	 */
	public static String splitEMailStr(String email) {
		String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		if(email.matches(regex)) {
			return email.split("@")[0];
		}else {
			return null;
		}
		
	}
	
	/**
	 * 判断字符串是否为null
	 * CommonUtil.isBlank(null)      = true
	   CommonUtil.isBlank("")        = true
	   CommonUtil.isBlank(" ")       = true
	   CommonUtil.isBlank("bob")     = false
	   CommonUtil.isBlank("  bob  ") = false
	   CommonUtil.isBlank("null")    = false
	 * @param str
	 * @return
	 */
	public static boolean strIsBlank(String str) {
		if(StringUtils.isBlank(str) || str.equals("null")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获得密码
	 * @param len 密码长度
	 * @return
	 */
	public static String createPassWord(int len){
		int random = createRandomInt();
		return createPassWord(random, len);
	}
	
	public static String createPassWord(int random,int len){
		Random rd = new Random(random);
		final int  maxNum = 62;
		StringBuffer sb = new StringBuffer();
		int rdGet;//取得随机数
		char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		int count=0;
		while(count < len){
			rdGet = Math.abs(rd.nextInt(maxNum));//生成的数最大为62-1
			if (rdGet >= 0 && rdGet < str.length) {
				sb.append(str[rdGet]);
			    count ++;
			}
		}
		return sb.toString();
	}
	
	public static int createRandomInt(){
		//得到0.0到1.0之间的数字，并扩大100000倍
		double temp = Math.random()*100000;
		//如果数据等于100000，则减少1
		if(temp>=100000){
			temp = 99999;
		}
		int tempint = (int)Math.ceil(temp);
		return tempint;
	}
	
	/**
	 * 判断该code是 省 市 区 街道的哪个code
	 * @param code
	 * @return
	 */
	public static String splitcode(String code) {
		int len = code.length();
		int index = 0;
		for(int i=len-1;i>=0;i--) {
			char[] arr = code.toCharArray();
			if(arr[i] != '0') {
				index = i;
				break;
			}
		}
		int newcode = code.substring(0, index+1).length();
		switch (newcode){
		case 2:
			return "省";
		case 4:
			return "市";
		case 6:
			return "区";
		case 9:
			return "街道";
		default:
			return "未识别";
		}
	}
	
	
	public static Long getOrderNO(long id) {
		String currenttime = System.currentTimeMillis() + "";
		currenttime = currenttime.substring(0, 10);
		return Long.valueOf(currenttime) + id;
	}
	
	public static void main(String[] args) throws Exception {
	}


}