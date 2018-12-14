package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.muteng.dgjs.domain.Organizational;

public interface OrganizationalService {

	List<Organizational> getList();
	//查询所有城市
	List<Map<String,Object>> queryOrganzation();
	List<Map<String,Object>> queryLive2();
	List<Organizational> queryByInitial(String initial);
	
}
