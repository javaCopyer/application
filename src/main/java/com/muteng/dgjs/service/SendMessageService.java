package com.muteng.dgjs.service;


import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface SendMessageService {

	String sendMessage(String phone);
	String sendSms(String phone,String content,String yaoqingma,String invitername) throws UnsupportedEncodingException;
	//报名成功后发送短信给邀请人和他的上级X5。。
	Boolean sendToInviters(Map<String,String> invitersMap);

}
