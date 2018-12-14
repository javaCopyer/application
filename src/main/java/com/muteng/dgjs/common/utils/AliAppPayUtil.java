package com.muteng.dgjs.common.utils;

import java.util.Map;
import java.util.Properties;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.muteng.dgjs.common.system.SpringContextHolder;

/**
 * 支付宝支付
 * 
 * @author ZC
 * @date 2018年10月29日 下午10:59:26
 * @version
 */
public class AliAppPayUtil {

	public static String appId;
	public static String privateKey;
	public static String notify_url;
	public static String aliPublicKey;
	static {
		Properties props = SpringContextHolder.getBean("props");
		appId = (String) props.get("pay.alipay.appId");
		privateKey = (String) props.get("pay.alipay.privateKey");
		notify_url = (String) props.get("pay.alipay.notify_url");
		aliPublicKey = (String) props.get("pay.alipay.aliPublicKey");
	}

	public static String order(Map<String, String> map) throws Exception {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, privateKey,
				"json", "UTF-8", aliPublicKey, "RSA2");
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(map.get("body"));
		model.setSubject(map.get("subject"));
		model.setOutTradeNo(map.get("out_trade_no"));
		model.setTimeoutExpress(map.get("timeout_express") == null ? "30m" : map.get("timeout_express"));
		model.setTotalAmount(map.get("total_amount"));
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(notify_url);
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		return response.getBody();
	}

}
