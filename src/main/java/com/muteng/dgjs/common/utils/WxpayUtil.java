package com.muteng.dgjs.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.muteng.dgjs.common.system.SpringContextHolder;
/**
 * 微信支付
* @author ZC  
* @date 2018年10月29日 下午10:46:26
* @version
 */
public class WxpayUtil {
	private static final Logger logger = LoggerFactory.getLogger(WxpayUtil.class);
	private static String appID;
	private static String mchID;
	private static String appgzhID;
	private static String mchgzhID;
	private static String userKey;
	private static String notify_url;
	static {
		Properties props =  SpringContextHolder.getBean("props");
		appID = (String) props.get("pay.wxpay.appID");
		mchID = (String) props.get("pay.wxpay.mchID");
		userKey = (String) props.get("pay.wxpay.userKey");
		notify_url = (String) props.get("pay.wxpay.notify_url");
		appgzhID = (String) props.get("pay.wxgzhpay.appID");
		mchgzhID = (String) props.get("pay.wxgzhpay.mchID");
	}

	/**
	 * APP支付
	 * @param out_trade_no
	 * @param total_amount
	 * @param subject
	 * @return
	 * @throws Exception
	 * @author ZC
	 * @date 2018年10月29日 下午10:44:54
	 */
	 @SuppressWarnings("unchecked")
	  public static List<Map<String,String>> appPay(String out_trade_no, String total_amount) throws Exception {
	    String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("appid", appID);
	    map.put("mch_id",mchID);
	    map.put("nonce_str", RandomStringUtils.randomAlphanumeric(32).toUpperCase());
	    map.put("body", "订单支付");
	    map.put("detail", "订单支付");
	    map.put("out_trade_no", out_trade_no);
	    map.put("total_fee", total_amount);
	    map.put("spbill_create_ip", "47.99.175.150");
	    map.put("notify_url", notify_url);
	    map.put("trade_type", "APP");
	    String sign = SignUtil.generateMD5(map, userKey, "UTF-8").toUpperCase();
	    map.put("sign", sign);

	    Document document = DocumentHelper.createDocument();
	    Element root = document.addElement("xml");
	    Set<String> keySet = map.keySet();
	    Iterator<String> iterator = keySet.iterator();
	    while (iterator.hasNext()) {
	      String mapKey = iterator.next();
	      String value = map.get(mapKey);
	      root.addElement(mapKey).setText(value);
	    }
	    String xml = document.asXML();
	    String result = HttpClientUtil.postXML(url, xml);
	    
	    logger.debug("微信APP支付响应数据==================:{}", result);
	    document = DocumentHelper.parseText(result);
	    root = document.getRootElement();
	    List<Element> elements = root.elements();
	    Map<String, Object> data = new HashMap<String, Object>();
	    for (int i = 0, len = elements.size(); i < len; i++) {
	      Element element = elements.get(i);
	      data.put(element.getName(), element.getText());
	    }
	    Map<String, String> newMap = new HashMap<String, String>();
	    newMap.put("appid", appID);
	    newMap.put("partnerid", mchID);
	    newMap.put("prepayid", data.get("prepay_id").toString());
	    newMap.put("noncestr", data.get("nonce_str").toString());
	    newMap.put("timestamp", System.currentTimeMillis() / 1000 + "");
	    newMap.put("package", "Sign=WXPay");
	    String paySign = SignUtil.generateMD5(newMap, userKey, "UTF-8").toUpperCase();
	    newMap.put("sign", paySign);
	    logger.debug("返回数据==:{}", newMap);
	    List<Map<String, String>> list = new LinkedList<Map<String, String>>();
	    list.add(newMap);
	    return list;
	  }



	 /**
	  * 公众号支付
	  * @param openid
	  * @param out_trade_no
	  * @param total_amount
	  * @param subject
	  * @return
	  * @throws Exception
	  * @author ZC
	  * @date 2018年10月29日 下午10:58:59
	  */
	 @SuppressWarnings("unchecked")
	 public static List<Map<String, String>> jsPay(String openid, String out_trade_no, String total_amount,String attach) throws Exception {
		 String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		    Map<String, String> map = new HashMap<String, String>();
		    map.put("openid", openid);
		    map.put("appid", appgzhID);
		    map.put("mch_id", mchgzhID);
		    map.put("nonce_str", RandomStringUtils.randomAlphanumeric(32).toUpperCase());
		    map.put("body", "订单支付");
		    map.put("detail", "订单支付");
		    map.put("out_trade_no", out_trade_no);
		    map.put("total_fee", total_amount);
		    map.put("spbill_create_ip", "47.99.175.150");
		    map.put("notify_url", notify_url);
		    map.put("trade_type", "JSAPI");
		    map.put("attach", attach);
		    String sign = SignUtil.generateMD5(map, userKey, "UTF-8").toUpperCase();
		    map.put("sign", sign);
		    Document document = DocumentHelper.createDocument();
		    Element root = document.addElement("xml");
		    Set<String> keySet = map.keySet();
		    Iterator<String> iterator = keySet.iterator();
		    while (iterator.hasNext()) {
		      String mapKey = iterator.next();
		      String value = map.get(mapKey);
		      root.addElement(mapKey).setText(value);
		    }
		    String xml = document.asXML();
		    String result = HttpClientUtil.postXML(url, xml);
		    logger.debug("微信公众号支付响应数据：=============={}", result);
		    document = DocumentHelper.parseText(result);
		    root = document.getRootElement();
		    List<Element> elements = root.elements();
		    Map<String, Object> data = new HashMap<String, Object>();
		    for (int i = 0, len = elements.size(); i < len; i++) {
		      Element element = elements.get(i);
		      data.put(element.getName(), element.getText());
		    }
		    Map<String, String> newMap = new HashMap<String, String>();
		    newMap.put("appId", appgzhID);
		    newMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		    newMap.put("nonceStr", data.get("nonce_str").toString());
		    newMap.put("package", "prepay_id="+data.get("prepay_id").toString());
		    newMap.put("signType", "MD5");
		    String paySign = SignUtil.generateMD5(newMap, userKey, "UTF-8").toUpperCase();
		    newMap.put("paySign", paySign);
		    List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		    list.add(newMap);
		    return list;
	  }

	 /**
	  * 回调验证签名
	  * @param xml
	  * @return
	  * @throws Exception
	  * @author ZC
	  * @date 2018年10月29日 下午10:46:03
	  */
	  @SuppressWarnings("unchecked")
	  public static boolean checkSign(String xml) throws Exception {
	    Document document = DocumentHelper.parseText(xml);
	    Element root = document.getRootElement();
	    List<Element> elements = root.elements();
	    Map<String, String> data = new HashMap<String, String>();
	    for (int i = 0, len = elements.size(); i < len; i++) {
	      Element element = elements.get(i);
	      data.put(element.getName(), element.getText());
	    }
	    String rightSign = data.get("sign");
	    String sign = SignUtil.generateMD5(data, userKey, "UTF-8").toUpperCase();
	    return sign.equals(rightSign);
	  }


}
