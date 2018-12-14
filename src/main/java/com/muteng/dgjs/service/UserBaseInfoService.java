package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.domain.BasicinFormation;
import com.muteng.dgjs.domain.UserBaseInfo;
import org.apache.ibatis.annotations.Select;

public interface UserBaseInfoService {
	int insert(Map<String, Object> map);
	int update(Map<String, Object> map);
	int deleteByUserid(Long userid,String type);
	UserBaseInfo queryByUseridAndName(Long userid, String name, Long value);
	//查询个人信息初始数据
	List<BasicinFormation> getBasicInfoMationByValue(String value);

	//查询个人信息数据
	List<BasicinFormation> getBasicInfoMationByValueAndId(String value,Long id);

	//查询信息类型数据
	List<BasicinFormation> getBasicInfoMation(Long id);

}
