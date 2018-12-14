package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Organizational;
public interface OrganizationalMapper extends DefaultMapper<Organizational>{

	//查询所有城市
	@Select("SELECT id,orgname,hot,initial FROM organizational where level=3  and status = '显示' group by orgname ORDER BY hot,initial")
	List<Map<String,Object>> queryOrganzation();

	@Select("SELECT orgname,level,hot,initial FROM organizational where level=3  and status = '显示' ORDER BY hot,initial")
	List<Organizational> queryListOrder();
	@Select("SELECT * FROM organizational where level = #{level} and id = #{parentid}")
	List<Organizational> queryLive2(Long parentid, Integer level);
	@Select("SELECT * FROM organizational where level = 3 and initial = #{initial} and status = '显示'")
	List<Organizational> queryByInitial(String  initial);
}
