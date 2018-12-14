package com.muteng.dgjs.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.muteng.dgjs.dao.*;
import com.muteng.dgjs.domain.*;
import com.muteng.dgjs.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.condition.LoginCondition;
import com.muteng.dgjs.service.Loginservice;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import javax.xml.transform.Source;

@Service
public class LoginserviceImple implements Loginservice{

	@Autowired
	private CaptchaMapper captchaMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserinfoMapper userinfoMapper;
	
	@Autowired
	private OrderpoolMapper orderpoolMapper;
	
	@Autowired
	private YaoqingmaMapper yaoqingmaMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private PayMapper payMapper;

	@Resource
	private PlatformTransactionManager transactionManager;

	@Override
	public Object[] doLogin(LoginCondition condition,Map<String,Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String msg = "";
		User resUser = null;
		String yaoqingma = "";
		Object[] obj = new Object[2];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", condition.getLoginname());
		Captcha captcha = this.captchaMapper.queryByPhone(map);
//		/**事务处理**/
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			if (captcha != null) {
				long effecttime = captcha.getEffectivetime();
				long catptchatime = captcha.getCreatetime().getTime();
				long nowtime = new Date().getTime();

				if (nowtime > (catptchatime + effecttime)) {
					obj[0] = 0;
					msg = "短信验证码过期";
				} else {
					Orderpool orderpool = new Orderpool();
					if (captcha.getCaptcha().intValue() == Integer.parseInt(condition.getSmsMessage())) {
						//根据openid查询是否有用户
						Map<String, Object> mp = new HashMap<String, Object>();
						mp.put("openid", condition.getWeixinopenid());
						UserInfo ui =userinfoMapper.queryUserInfoByOpenid(mp);
						if(ui !=null){
							User u =new User();
							u.setId(ui.getUserid());
							u.setLoginname(condition.getLoginname());
							u.setPhone(condition.getLoginname());
							userMapper.updateByPrimaryKeySelective(u);
						}
						//根据手机号去查询用户
						User user = this.userMapper.queryByloginname(map);
						System.out.println(user);
						if (user == null) {
							// 新用户
							User u = new User();
							u.setLoginname(condition.getLoginname());
							u.setCreatetime(new Date());
							u.setUpdatetime(new Date());
							u.setUsertype(10);
							u.setLoginstatus(1);
							u.setSource(condition.getSource());
							u.setSubsource(condition.getSubsource());
							u.setAppversion("打工集市1.0");
							if (StringUtil.isNotEmpty(condition.getInvitation())) {
								u.setIsinviter("是");
								u.setInviter(condition.getInvitation());
							} else {
								u.setIsinviter("否");
							}
							int id =this.userMapper.insertuser(u);
							UserPool up = new UserPool();
							up.setUserid(u.getId());
							up.setLoginname(condition.getLoginname());
							up.setIs_entry(0);
							up.setRegistrationtime(u.getCreatetime());
							up.setCreatetime(new Date());
							this.userMapper.insertuserpool(up);
							user = u;
							resUser = u;
						} else {
							//判断是否给邀请人奖励
							if (user.getLoginstatus() !=null && user.getLoginstatus() == 0 &&user.getInviter() != null) {
								//根据邀请人邀请码查询到邀请人
								User inviter = yaoqingmaMapper.queryByyaoqingma(user.getInviter());
								if (inviter != null) {
									//邀请人添加账单
									Account account = new Account();
									account.setUserid(inviter.getId());
									account.setAmount(200);
									account.setType(2);
									account.setReason(2);
									account.setCreatetime(new Date());
									account.setUpdatetime(new Date());
									account.setStatus(3);
									account.setComment("邀请入职");
									payMapper.insertAccount(account);
									//更新邀请人账户余额
									inviter.setAccount(inviter.getAccount() + 200);
									userMapper.updateByPrimaryKey(inviter);
								}
							}
							//根据userid查询是否 已经绑定openid，
							map.put("userid", user.getId());
							UserInfo userInfo = this.userinfoMapper.queryUserInfoByUserid(map);

							//如果没有绑定则绑定，如果已经绑定则修改最新微信头像，昵称等信息
							if (userInfo == null) {
								//没有绑定过，插入
								userInfo = new UserInfo();
								userInfo.setUserid(user.getId());
								userInfo.setCreatetime(new Date());
								if (paramMap.get("weixinopenid") != null) {
									this.updateUserInfo(paramMap, userInfo);
									this.userinfoMapper.insert(userInfo);
								}
							} else {
								//已经绑定过，更新
								userInfo.setUserid(user.getId());
								if (paramMap.get("weixinopenid") != null) {
									this.updateUserInfo(paramMap, userInfo);
									this.userinfoMapper.updateUserinfo(userInfo);
								}
							}
							// 老用户
							orderpool.setName(user.getName());
							Yaoqingma yaoqingmaBean = yaoqingmaMapper.queryById(user.getId());
							yaoqingma = yaoqingmaBean.getYaoqingma();
							user.setYaoqingma(yaoqingma);
							user.setLoginstatus(1);
							this.userMapper.updateByPrimaryKeySelective(user);
							resUser = user;
						}

						if (user.getId() > 0) {
							Map<String, Object> mapDevice = new HashMap<String, Object>();
							mapDevice.put("uuid", paramMap.get("uuid"));
							Device d = this.deviceMapper.queryByuuid(mapDevice);
							if (d == null) {
								//保存设备信息
								Device device = new Device();
								device.setUserid((long) user.getId());
								device.setChannelid(String.valueOf(paramMap.get("channelid")));
								device.setCreatetime(new Timestamp(System.currentTimeMillis()));
								device.setDevice(String.valueOf(paramMap.get("device")));
								device.setImei(String.valueOf(paramMap.get("imei")));
								device.setOs(String.valueOf(paramMap.get("os")));
								device.setOsVersion(String.valueOf(paramMap.get("osVersion")));
								device.setFactory(String.valueOf(paramMap.get("factory")));
								device.setUuid(String.valueOf(paramMap.get("uuid")));
								this.deviceMapper.insert(device);
							}
						}
						obj[0] = 1;
						msg = "成功";
					} else {
						obj[0] = 0;
						msg = "短信验证码错误";
					}
					orderpool.setLoginname(condition.getLoginname());
					orderpool.setOrdertime(new Date());
					orderpool.setCreatetime(new Date());
					this.orderpoolMapper.insert(orderpool);
				}
			} else {
				obj[0] = 0;
				msg = "输入短信验证码";
			}
			resMap.put("msg", msg);
			if (resUser != null) {
				resMap.put("userid", resUser.getId());
				resMap.put("loginname", resUser.getLoginname());
			}
			obj[1] = resMap;
//			transactionManager.commit(status);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
//			transactionManager.rollback(status);//事务回滚
			obj[0] = 0;
			msg = "输入短信验证码";
			resMap.put("msg", msg);
			obj[1] = resMap;
			return obj;
		}
	}

	public void updateUserInfo(Map<String, Object> paramMap, UserInfo userInfo) {
		userInfo.setWeixin_openid(String.valueOf(paramMap.get("weixinopenid")));
		if(paramMap.get("weixinprovince") != null){
			userInfo.setProvince(String.valueOf(paramMap.get("weixinprovince")));
		}
		if(paramMap.get("weixincity") != null) {
			userInfo.setCity(String.valueOf(paramMap.get("weixincity")));
		}
		if(paramMap.get("weixinnickname") != null) {
			userInfo.setNickname(String.valueOf(paramMap.get("weixinnickname")));
		}
		if(paramMap.get("weixinheadimgurl") != null) {
			userInfo.setHeadimgurl(String.valueOf(paramMap.get("weixinheadimgurl")));
		}
		if(paramMap.get("weixinsex") != null) {
			userInfo.setSex(Integer.parseInt(paramMap.get("weixinsex").toString()));
		}
	}
	
	/**
	 * 是否已经绑定微信
	 * 绑定了，返回userid 和 loginname
	 * @return 
	 */
	public Object[] isBandingWeixin(Map<String,Object> paramMap) {
		Object [] obj = new Object[2];
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(paramMap == null || paramMap.get("weixinopenid") == null) {
			obj[0] = 0;
			result.put("isbanding", "0");
			result.put("msg", "参数缺失");
			obj[1] = result; 
		}
		
		UserInfo userInfo = this.userinfoMapper.queryUserInfoByOpenid(paramMap);
		if(userInfo == null || userInfo.getUserid() == null) {
			//返回没有绑定
			obj[0] = 1;
			result.put("isbanding", "0");
			obj[1] = result;
			return obj;
		}
		//查询userid 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userInfo.getUserid());
		User user = this.userMapper.queryByUserId(map);
		if(user == null) {
			obj[0] = 1;
			result.put("isbanding", "0");
			obj[1] = result;
			//返回没有绑定
			return obj;
		}
		
		if(paramMap.get("weixinopenid") != null){
			this.updateUserInfo(paramMap, userInfo);
			this.userinfoMapper.updateUserinfo(userInfo);
		}
		
		obj[0] = 1;
		result.put("isbanding", "1");
		result.put("userid", user.getId());
		result.put("loginname", user.getLoginname());
		obj[1] = result;
		return obj;
	}

	@Override
	public Object[] doMessageLogin(LoginCondition condition, Map<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String msg = "";
		User resUser = null;
		String yaoqingma = "";
		Object[] obj = new Object[2];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", condition.getLoginname());
		Captcha captcha = this.captchaMapper.queryByPhone(map);
		if (captcha != null) {
			long effecttime = captcha.getEffectivetime();
			long catptchatime = captcha.getCreatetime().getTime();
			long nowtime = new Date().getTime();

			if (nowtime > (catptchatime + effecttime)) {
				obj[0] = 0;
				msg = "短信验证码过期";
			} else {
				Orderpool orderpool = new Orderpool();
				if (captcha.getCaptcha().intValue() == Integer.parseInt(condition.getSmsMessage())) {
					//根据手机号去查询用户
					User user = this.userMapper.queryByloginname(map);
					if (user == null) {
						// 新用户
						User u = new User();
						u.setLoginname(condition.getLoginname());
						u.setCreatetime(new Date());
						u.setUpdatetime(new Date());
						u.setUsertype(10);
						u.setSubsource(condition.getSubsource());
						u.setSource(condition.getSource());
						//判断用户是否有邀请码
						if (StringUtil.isNotEmpty(condition.getInvitation())) {
							//根据UUID判断登陆用户设备是否存在
							Map<String, Object> mapDevice = new HashMap<String, Object>();
							mapDevice.put("uuid", paramMap.get("uuid"));
							Device d = this.deviceMapper.queryByuuid(mapDevice);
							if (d == null) {
								//新用户设置邀请人
								u.setInviter(condition.getInvitation());
								u.setIsinviter("是");
								u.setLoginstatus(0);
							}else{
								obj[0] = 0;
								msg = "同一台设备只能接受一次邀请";
								resMap.put("msg", msg);
								obj[1] = resMap;
								return obj;
							}
						} else {
							u.setIsinviter("否");
							u.setLoginstatus(1);
						}
						this.userMapper.insertuser(u);
						UserPool up = new UserPool();
						up.setUserid(u.getId());
						up.setLoginname(condition.getLoginname());
						up.setIs_entry(0);
						up.setRegistrationtime(u.getCreatetime());
						up.setCreatetime(new Date());
						this.userMapper.insertuserpool(up);
						user = u;
						resUser = user;
					} else {
						obj[0] = 0;
						msg = "该手机号已经注册";
						resMap.put("msg", msg);
						obj[1] = resMap;
						return obj;
					}
					obj[0] = 1;
					msg = "成功";
				} else {
					obj[0] = 0;
					msg = "短信验证码错误";
				}
				orderpool.setLoginname(condition.getLoginname());
				orderpool.setOrdertime(new Date());
				orderpool.setCreatetime(new Date());
				this.orderpoolMapper.insert(orderpool);
			}
		} else {
			obj[0] = 0;
			msg = "输入短信验证码";
		}
		resMap.put("msg", msg);
		if(resUser != null) {
			resMap.put("userid", resUser.getId());
			resMap.put("loginname", resUser.getLoginname());
		}
		obj[1] = resMap;
		return obj;
	}
}
