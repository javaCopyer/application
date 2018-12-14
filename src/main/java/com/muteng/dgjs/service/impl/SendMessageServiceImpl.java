package com.muteng.dgjs.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.muteng.dgjs.DTO.ProductDetailDTO;
import com.muteng.dgjs.common.utils.HttpUtils;
import com.muteng.dgjs.common.utils.PasswordED;
import com.muteng.dgjs.dao.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.common.utils.SendMessageUtil;
import com.muteng.dgjs.domain.Captcha;
import com.muteng.dgjs.domain.Smslog;
import com.muteng.dgjs.service.SendMessageService;

@Service
public class SendMessageServiceImpl implements SendMessageService{

	@Autowired
	private CaptchaMapper captchaMapper;
	
	@Autowired
	private SmslogMapper smslogMapper;

	@Resource
	private UserMapper usermapper;

	@Resource
	private ProductlistMapper productlistMapper;

	
	@Override
	public String sendMessage(String phone) {

		String content = "";
		String rad = random();
		content = "【打工集市】"+"您的验证码是：" +rad+"请在页面提交验证码完成验证。" ;
		String vo = SendMessageUtil.send(phone, content);

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("phone", phone);
			Captcha ca = this.captchaMapper.queryByPhone(map);
			if(ca != null){
				this.captchaMapper.delete(ca);
			}
			
			Captcha captcha = new Captcha();
			captcha.setMobile(Long.parseLong(phone));
			captcha.setCaptcha(Integer.parseInt(rad));
			captcha.setCreatetime(new Timestamp(System.currentTimeMillis()));
			captcha.setEffectivetime(60000);
			this.captchaMapper.insert(captcha);
		return vo;
	}

	private String random(){
		String str="0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++){
			char ch=str.charAt(new Random().nextInt(str.length()));
			if(ch == '0') {
				ch = '1';
			}
			sb.append(ch);
		}
		return sb.toString();
	}

	@Override
	public String sendSms(String phone, String name,String yaoqingma,String invitername) throws UnsupportedEncodingException {
		String content = "";
		PasswordED passwordED =new PasswordED();
		String pass ="sharetype="+4+",subsource="+"\""+"APP短信分享"+"\""+",yaoqingma="+"\""+yaoqingma+"\"";
		String enpass= passwordED.encPassword(pass);
		Map<String, String> getMap = new HashMap<String, String>();
		getMap.put("source", "21912655");
		getMap.put("url_long", "http://workbazaar.lanlingzhifu.cn/weixin/weixin.jsp?pass=" + URLEncoder.encode(enpass,"UTF-8"));
		String result = HttpUtils.doGet("http://api.t.sina.com.cn/short_url/shorten.json", getMap);
		JSONArray array = JSONArray.parseArray(result);
		com.alibaba.fastjson.JSONObject jo = array.getJSONObject(0);
		String url_short = jo.getString("url_short");
		System.out.println("============================================"+url_short);
		content = "【打工集市】您的好友"+invitername+"邀您使用打工集市app，在外打工多挣钱更放心，快来看看吧， "+url_short+" 回TD退订";
		String vo = SendMessageUtil.send(phone, content);
		JSONObject json =  JSONObject.fromObject(vo);
		Smslog smslog = new Smslog();
		smslog.setMobile(Long.parseLong(phone));
		smslog.setContent(content);
		String msgid =json.optString("msgid");
		long msgL = 0;
		if(!msgid.equals("null")){
			msgL = Long.valueOf(msgid);
		}
		smslog.setTaskid(msgid);
		smslog.setCreatetime(new Date());
		this.smslogMapper.insert(smslog);

		return vo;
	}

	//报名成功后发送短信给邀请人和他的上级X5。。
	@Override
	public Boolean sendToInviters(Map<String,String> invitersMap){
			String  babyname =invitersMap.get("name");
		try {
			List<ProductDetailDTO> ProductDetail = productlistMapper.queryByProductid(Long.valueOf(String.valueOf(invitersMap.get("productid"))));
			String content = "";
			if (invitersMap.get("inviteduserid") != null) {
				int i = 0;
				do {
					if (invitersMap.get("inviteduserid") == null) {
						return true;
					} else {
						invitersMap = usermapper.heighLevelMap(Integer.parseInt(String.valueOf(invitersMap.get("inviteduserid"))));
						content = "【打工集市】" + invitersMap.get("name") + "您好，您的好友"+babyname+"成功报名"+ProductDetail.get(0).getLongtitle()+"请联系好友尽快参与面试和入职！";
						System.out.println("==========================="+invitersMap.get("loginname")+"===========================");
						String vo = SendMessageUtil.send(invitersMap.get("loginname"), content);
						JSONObject json =  JSONObject.fromObject(vo);
						String msgid =  (String)json.optString("msgid");
						long msgL = 0;
						if(!msgid.equals("null")){
						msgL = Long.valueOf(msgid);
						}
						smslogMapper.insertMessage(Long.valueOf(invitersMap.get("loginname")), content, msgL);
						if (!vo.equals("false")) {

						}
					}

					i++;
				} while (i < 5);
			}

			return true;
		}
		catch (Exception e){
			System.out.println("发送短信给邀请人出错");
			e.printStackTrace();
			return false;
		}
		}


}
