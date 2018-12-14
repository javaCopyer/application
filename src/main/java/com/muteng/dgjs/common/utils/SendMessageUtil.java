package com.muteng.dgjs.common.utils;

import java.net.URLEncoder;
import com.bcloud.msg.http.HttpSender;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendMessageUtil {

//	private final static String account = "lanzhipei-tz";
//	private final static String password = "FD51016E3995E633A710F9334D042F90";

	public static String send(String mobile,String msg){

		String uri = "http://114.55.25.138/msg/HttpBatchSendSM";//应用地址
		String account = "lzptz2018";//账号
		String pswd = "lZptz2018@";//密码
		boolean needstatus = true;//是否需要状态报告，需要true，不需要false
		String product = "";//产品ID
		String extno = "";//扩展码
		String respType = "json";//返回json格式响应
		boolean encrypt = false;// 密码使用时间戳加密
		try {
			String returnString = HttpSender.send(uri, account, pswd, mobile,msg, needstatus, product, extno, respType, encrypt);
			return returnString;
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

}
