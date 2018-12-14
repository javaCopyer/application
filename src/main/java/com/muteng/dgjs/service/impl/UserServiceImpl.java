package com.muteng.dgjs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.DTO.ClientsDTO;
import com.muteng.dgjs.DTO.UserSetting;
import com.muteng.dgjs.common.utils.IdcardValidator;
import com.muteng.dgjs.dao.CaptchaMapper;
import com.muteng.dgjs.dao.UserMapper;
import com.muteng.dgjs.domain.Captcha;
import com.muteng.dgjs.domain.User;
import com.muteng.dgjs.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CaptchaMapper captchaMapper;
	
	//查询首页会员数量
	@Override
	public int getMemberInfo(Map<String, Object> map){
		User u=userMapper.queryByloginname(map);
		Map<String,Object> mp=new HashMap<>();
		mp.put("id",u.getId());
		int a =this.userMapper.querymembercount(mp);
		return a;
	}

	//												查询用户类型，头像
	@Override
	public Map<String,Object> getUserInfo(Map<String, Object> map) {
		Map<String,Object> reusltMap = new HashMap<>();
				reusltMap = userMapper.querymember(map);
		if(Integer.parseInt(String.valueOf(reusltMap.get("usertype")))>=8){
			Object timeObj = userMapper.memberTime(reusltMap);
			reusltMap.put("time",timeObj);
		}


		return reusltMap;
	}

	@Override
	public Long getUserRz(Long userid) {
		Long userRz = this.userMapper.queryRZ(userid);
		return userRz;
	}

	@Override
	public ClientsDTO getInviter(Map<String, Object> map) {

		ClientsDTO clientsDTO = new ClientsDTO();
		User user = this.userMapper.queryByloginname(map);
		if(user == null){
			clientsDTO.setInvitednumber(0);
			clientsDTO.setInviter(null);
		}else{
			clientsDTO.setInvitednumber(user.getInvitednumber());
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("inviter", user.getInviter());
			List<User> list = this.userMapper.queryinviter(map2);
			clientsDTO.setInviter(list);
		}
		return clientsDTO;
	}


	//查询首页客户通过我邀请码注册的用户和关注我的人
	@Override
	public int getinvited(Map<String,Object> map){
		return this.userMapper.querinvited(Long.valueOf(String.valueOf(map.get("phone"))))+this.userMapper.queryattentioncount(map);

	}




	@Override
	public Object[] updateUser(Map<String,Object> paramMap) {
		Object[] obj = new Object[2];
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(paramMap != null){

			if(paramMap.get("loginname") != null) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("phone", paramMap.get("loginname"));
				Captcha captcha = this.captchaMapper.queryByPhone(map);
				if(captcha.getCaptcha().intValue() != Integer.parseInt(paramMap.get("smsMessage").toString())){
					obj[0] = 0;
					resMap.put("msg", "短信验证码错误");
					obj[1] = resMap;
					return obj;
				}
			}


			User user = this.userMapper.queryByUserId(paramMap);
			if(user != null){
				if(paramMap.get("name") != null) {
					user.setName(paramMap.get("name").toString());
				}
				if(paramMap.get("loginname") != null) {
					user.setLoginname(paramMap.get("loginname").toString());
				}
				if(paramMap.get("idcard") != null) {
					IdcardValidator idcardValidate = new IdcardValidator();
					if(idcardValidate.isValidatedAllIdcard(paramMap.get("idcard").toString())) {
						user.setIdcard(paramMap.get("idcard").toString());
					} else {
						obj[0] = 0;
						resMap.put("msg", "身份证格式有误");
						obj[1] = resMap;
						return obj;
					}
				}
				this.userMapper.updateByPrimaryKey(user);
				resMap.put("msg", "修改成功");
				obj[0] = 1;

			}else{
				resMap.put("msg", "修改的用户不存在");
				obj[0] = 0;
			}
		} else {
			resMap.put("msg", "参数有误");
			obj[0] = 0;
		}
		obj[1] = resMap;
		return obj;
	}

	@Override
	public User getUserById(Long id) {
		User user = this.userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public UserSetting getUserInfoById(Long id) {
		return userMapper.queryById(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		return this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public UserSetting getWxcard(Long id) { return userMapper.queryWxById(id); }

	@Override
	public void getMoney(Long userid,Long amount) {
		userMapper.getMoney(userid,amount);
	}

	@Override
	public UserM getInfoById(Long id) {
		return userMapper.getInfoById(id);
	}

	//返回自增主键
	@Override
	public int adduser(User u) {
		return userMapper.insert(u);
	}

	//返回自增主键
	@Override
	public Long insertuser(User u) {
		userMapper.insertuser(u);
		return u.getId();
	}
	//根据手机查询用户
	@Override
	public User getUserByPhone(String loginname) {
		return userMapper.getUserByPhone(loginname);
	}

	@Override
	public void deleteUserByPhone(String loginname) {
		userMapper.deleteUserByPhone(loginname);
	}


}
