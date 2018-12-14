package com.muteng.dgjs.service;

import java.util.Map;

import com.muteng.dgjs.DTO.ClientsDTO;
import com.muteng.dgjs.DTO.UserM;
import com.muteng.dgjs.DTO.UserSetting;
import com.muteng.dgjs.domain.User;

/**
 * date: 2018/10/20 00:20
 * author: xuhaohaoxu
 * updater:tangzhihua
 * describe:
 */
public interface UserService {
	
	int getMemberInfo(Map<String,Object> map);

	//							查询首页客户通过我邀请码注册的用户和关注我的人
	int getinvited(Map<String,Object> map);

	//												查询用户类型，头像
	Map<String,Object> getUserInfo(Map<String,Object> map);

	//										method32根据id查询个人设置
	UserSetting getUserInfoById(Long id);
	
	Long getUserRz(Long userid);
	
	ClientsDTO getInviter(Map<String,Object> map);
	
	Object[] updateUser(Map<String,Object> paramMap);
	
	User getUserById(Long id);
	
	int updateByPrimaryKeySelective(User user);

	//进入微信名片
	UserSetting getWxcard(Long id);

	//取现
	void getMoney(Long userid,Long amount);

	UserM getInfoById(Long id);
	//添加用户
    int adduser(User u);

    //添加用户返回主键
	Long insertuser(User u);

	User getUserByPhone(String loginname);

	//根据手机删除用户
	void deleteUserByPhone(String loginname);
}
