package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.domain.OrdersMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.UserinfoMapper;
import com.muteng.dgjs.domain.UserInfo;
import com.muteng.dgjs.service.UserinfoService;

import java.util.List;

@Service
public class UserinfoServiceImpl implements UserinfoService {

	@Autowired
	private UserinfoMapper userinfoMapper;
	@Override
	public int updateUserinfo(UserInfo userinfo) {
		return userinfoMapper.updateUserinfo(userinfo);
	}

	@Override
	public void addUserinfo(UserInfo ui) {
		userinfoMapper.insert(ui);
	}

	@Override
	public OrdersMember getordererByid(Long id) {
		return userinfoMapper.getordererByOpenid(id);
	}

	@Override
	public void deleteUserInfo(String orderNo) {
		userinfoMapper.deleteUserInfo(orderNo);
	}

	@Override
	public UserInfo getInfoByopenid(String openid) {
		return userinfoMapper.getInfoByopenid(openid);
	}

}
