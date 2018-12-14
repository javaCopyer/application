package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.BasicinFormation;
import com.muteng.dgjs.domain.User;
import com.muteng.dgjs.domain.UserBaseInfo;
public interface UserBaseInfoMapper extends DefaultMapper<User>{

	@Update("insert into userbaseinfo(userid, name, value, createtime) values(#{userid}, #{name}, #{value}, #{createtime})")
	int insert1(Map<String, Object> map);

	@Update("update userbaseinfo set value = #{value}, createtime = #{createtime} where userid = #{userid} and name = #{name}")
	int update(Map<String, Object> map);
	//根据用户ID删除需要修改字段个人信息
	@Update("delete from userbaseinfo where userid = #{param1}" +
			" and name = #{param2}")
	int deleteByUserid(Long userid,String type);
	//根据用户id和个人信息类型查询所有个人信息""
	@Select("select * from userbaseinfo where userid = #{userid} and name = #{name} and value = #{value}")
	UserBaseInfo queryByUseridAndNameAndValue(Long userid, String name, Long value);

	//查询个人信息初始数据
	@Select("SELECT b.name,b.value,b.id as code FROM basicinformation b" +
			" WHERE b.value = #{value} and name!='非四大族' and id not in (5,6,7,8,9)")
	List<BasicinFormation> getBasicInfoMationByValue(String value);

	//查询个人信息数据
	@Select("SELECT * FROM basicinformation b" +
			" left join userbaseinfo ui on b.id=ui.value" +
			" WHERE b.value = #{param1} ")
	List<BasicinFormation> getBasicInfoMationByValueAndId(String value,Long id);
	//查询个人信息初始数据
	@Select("SELECT b.name,b.value,b.id FROM basicinformation b" +
			" left join userbaseinfo ui on  b.id=ui.value" +
			" where userid = #{id}" +
			" and ui.name != '意向职位'" +
			" and ui.name != '意向城市'")
	List<BasicinFormation> getBasicInfoMation(Long id);

	//查询个人信息意向城市初始数据
	@Select("SELECT b.jobtitle as name,b.producttype as value,b.id FROM positions b" +
			" left join userbaseinfo ui on  b.id=ui.value" +
			" where ui.userid = #{id}" +
			" and ui.name = '意向职位' ")
	List<BasicinFormation> getCityInfoMation(Long id);

	//查询个人信息意向工作初始数据
	@Select("SELECT b.orgname as name,ui.name as value,b.id FROM organizational b" +
			" left join userbaseinfo ui on  b.id=ui.value" +
			" where ui.userid = #{id}"+
			" and ui.name = '意向城市' ")
	List<BasicinFormation> getWorkInfoMation(Long id);
}
