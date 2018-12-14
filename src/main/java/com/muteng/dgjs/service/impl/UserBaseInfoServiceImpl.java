package com.muteng.dgjs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.UserBaseInfoMapper;
import com.muteng.dgjs.domain.BasicinFormation;
import com.muteng.dgjs.domain.UserBaseInfo;
import com.muteng.dgjs.service.UserBaseInfoService;
@Service
public class UserBaseInfoServiceImpl implements UserBaseInfoService {
	@Autowired
	private UserBaseInfoMapper userBaseInfoMapper;
	@Override 
	public int insert(Map<String, Object> map) {
		return userBaseInfoMapper.insert1(map);
	}

	@Override
	public int update(Map<String, Object> map) {
		return userBaseInfoMapper.update(map);
	}

	@Override
	public UserBaseInfo queryByUseridAndName(Long userid, String name, Long value) {
		return userBaseInfoMapper.queryByUseridAndNameAndValue(userid, name, value);
	}

	@Override
	public int deleteByUserid(Long userid,String type) {
		return userBaseInfoMapper.deleteByUserid(userid,type);
	}

	@Override
	public List<BasicinFormation> getBasicInfoMationByValue(String value) {
		return userBaseInfoMapper.getBasicInfoMationByValue(value);
	}

	@Override
	public List<BasicinFormation> getBasicInfoMationByValueAndId(String value, Long id) {
		return userBaseInfoMapper.getBasicInfoMationByValueAndId(value,id);
	}

	@Override
	public List<BasicinFormation> getBasicInfoMation(Long id) {
		List<BasicinFormation> list =new ArrayList<>();
		List<BasicinFormation> list1;
		List<BasicinFormation> list2;
		List<BasicinFormation> list3;
		list1 = userBaseInfoMapper.getBasicInfoMation(id);
		list2 = userBaseInfoMapper.getCityInfoMation(id);
		list3 = userBaseInfoMapper.getWorkInfoMation(id);
		list.addAll(list1);
		list.addAll(list2);
		list.addAll(list3);
		return list;
	}

}
