package com.muteng.dgjs.service;

import com.muteng.dgjs.domain.OrdersMember;
import com.muteng.dgjs.domain.UserInfo;

import java.util.List;

public interface UserinfoService {
	int updateUserinfo(UserInfo userinfo);

	void addUserinfo(UserInfo ui);

    OrdersMember getordererByid(Long id);

	void deleteUserInfo(String orderNo);

	UserInfo getInfoByopenid(String openid);

}
