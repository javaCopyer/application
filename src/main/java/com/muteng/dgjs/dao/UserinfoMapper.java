package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.domain.OrdersMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.UserInfo;
public interface UserinfoMapper extends DefaultMapper<UserInfo>{
	@Update("<script>update userinfo" + 
			"    <set>" + 
			"      <if test=\"weixin_openid != null\">" + 
			"        weixin_openid = #{weixin_openid,jdbcType=VARCHAR}," + 
			"      </if>" + 
			"      <if test=\"nickname != null\">" + 
			"        nickname = #{nickname,jdbcType=VARCHAR}," + 
			"      </if>" + 
			"      <if test=\"sex != null\">" + 
			"        sex = #{sex,jdbcType=TINYINT}," + 
			"      </if>" + 
			"      <if test=\"province != null\">" + 
			"        province = #{province,jdbcType=VARCHAR}," + 
			"      </if>" + 
			"      <if test=\"city != null\">" + 
			"        city = #{city,jdbcType=VARCHAR}," + 
			"      </if>" + 
			"      <if test=\"headimgurl != null\">" + 
			"        headimgurl = #{headimgurl,jdbcType=VARCHAR}," + 
			"      </if>" + 
			"      <if test=\"createtime != null\">" + 
			"        createtime = #{createtime,jdbcType=TIMESTAMP}," + 
			"      </if>" + 
			"    </set>" + 
			"    where userid = #{userid,jdbcType=BIGINT}</script>")
	int updateUserinfo(UserInfo userinfo);
	
	@Select("select * from userinfo where userid=#{userid}")
	UserInfo queryUserInfoByUserid(Map<String,Object> paramMap);

	@Select("select * from userinfo where weixin_openid=#{weixinopenid}")
	UserInfo queryUserInfoByOpenid(Map<String,Object> paramMap);

	@Select("select * from ordersmember where userid=#{id} order by finishtime desc limit 1")
	OrdersMember getordererByOpenid(Long id);

	@Delete("delete from userinfo where weixin_openid=#{openid}")
	void deleteUserInfo(String openid);

	@Select("select * from userinfo where weixin_openid=#{openid} order by createtime desc limit 1")
    UserInfo getInfoByopenid(String openid);

}
