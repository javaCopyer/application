package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.UserM;
import com.muteng.dgjs.DTO.UserSetting;
import com.muteng.dgjs.domain.ReceiptDocument;
import com.muteng.dgjs.domain.Receiptvoucher;
import com.muteng.dgjs.domain.UserPool;
import org.apache.ibatis.annotations.*;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.UserOrderlistDTO;
import com.muteng.dgjs.domain.User;

public interface UserMapper extends DefaultMapper<User>{

	@Select("SELECT id,loginname,name,createtime,loginstatus,inviter FROM user WHERE loginname = #{phone} order by id desc limit 1")
	User queryByloginname(Map<String,Object> map);

	//查询用户类型，头像
	@Select("SELECT u.name,u.usertype,ui.nickname,ui.headimgurl,u.id FROM user as u\n" +
			"\t\t\tleft join userinfo ui on ui.userid=u.id\n" +
			"\t\t\twhere u.loginname=#{phone}")
	Map<String,Object> querymember(Map<String,Object> map);


	//查询首页会员数量
	@Select("select count(*) from  orderagencymoney" +
			" where userid = #{id} and ordertype = '会员'  ")
	int querymembercount(Map<String,Object> map);


	//查询我的邀请码邀请的用户
	@Select("SELECT count(*) FROM user as u where u.inviter=(" +
            "select yaoqingma from yaoqingma where id=(select id from user where loginname=#{phone}))")
	int querinvited(Long phone);

	//查询我的邀请码邀请的用户
	@Select("SELECT count(*) FROM `user`  WHERE inviter =(\n" +
			"         SELECT yaoqingma FROM `user` WHERE id=#{userid})")
	int querinvitedByid(Long userid);

	//查询关注我的用户
	@Select("SELECT count(*) FROM relation where touserid = #{userid} ")
	int queryattentioncount(Map<String,Object> map);

	//获取设置页面个人信息
	@Select("SELECT u.name,u.loginname as phone,u.qq,u.weixinurl,u.id as userid ,u.idcard,ui.headimgurl,ui.nickname,u.weixin FROM user u" +
			" left join userinfo ui on u.id= ui.userid" +
			" WHERE u.id = #{id}")
	UserSetting queryById(Long id);
	
	@Select("SELECT * FROM user WHERE inviter = #{inviter}")
	List<User> queryinviter(Map<String,Object> map);

	@Select("\n" +
			"SELECT count(*) FROM orderagencymoney  oam\n" +
			"LEFT JOIN orderlist ol on oam.orderid = ol.orderid\n" +
			"WHERE oam.userid =#{userid}  and ol.status in ('入职-返费确认中','收到返费-结束','离职','供应商拖欠','申请离职') and oam.ordertype= '招工'")
	Long queryRZ(Long userid);
	
	@Select("SELECT * FROM user WHERE id = #{userid}")
	User queryByUserId(Map<String,Object> map);

	@Select("SELECT ui.headimgurl,ui.nickname,u.weixinurl FROM user u" +
			" left join userinfo ui on u.id=ui.userid" +
			" WHERE u.id = #{userid}")
	UserSetting queryWxById(Long id);
    //修改账户余额
	@Update("update user set account = account - #{param2}" +
			" where id = #{param1}")
	void getMoney(Long userid,Long amount);

	//查询取现相关数据
	@Select("select u.name,u.account,u.id as userid from user u where id = #{userid}")
	UserM getInfoById(Long userid);


	//取成为会员的时间
	@Select("SELECT payfinishtime from ordersmember where userid = #{id} ORDER BY payfinishtime desc LIMIT 1")
	Object memberTime(Map<String,Object> map);

	//插入用户返回主键
	@Insert("insert into user (usertype,parentid,isinviter,createtime,loginname,loginstatus) values (#{usertype},#{parentid},#{isinviter},#{createtime},#{loginname},#{loginstatus})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = long.class)
	int insertuser(User u);

	//根据手机查询用户
	@Select("select * from user where loginname = #{loginname}")
	User getUserByPhone(String loginname);

	@Delete("delete from user where loginname = #{loginname}")
    void deleteUserByPhone(String loginname);

	////userid对应的人的上级的姓名，手机号，userid。
	@Select("SELECT  parentid,name,loginname \n" +
			"FROM user \n" +
			"where id =#{userid}")
	Map<String,String> heighLevelMap(Integer userid);

	//根据手机查询是否是公司员工
	@Select("select name from accessuser where " +
			"workmobile = #{phone} order by createtime desc limit 1")
	String queryStaffByPhone(String phone);

	//查询我的直接会员邀请
	@Select("select count(*) from  orderagencymoney " +
			"where userid = #{id} and level = 1 and ordertype = '会员'")
	int queryMymembercount(Map<String, Object> map);

	//查询我的间接会员邀请
	@Select("select count(*) from  orderagencymoney " +
			"where userid = #{id} and level !=1 and ordertype = '会员'")
	int queryOmembercount(Map<String, Object> map);

	//查询我的所有会员邀请详情
	@Select(" ")
	List<Map<String,Object>> queryAmember(Map<String, Object> map);

	//插入userpool
	@Insert("insert into userpool (userid,loginname,is_entry,registrationtime,createtime) " +
			" values (#{userid},#{loginname},#{is_entry},#{registrationtime},#{createtime},)")
    void insertuserpool(UserPool up);

	//插入收款凭证
	@Insert("insert into receipt_voucher (orderid,ordertype,price,userid,name,comment,createtime,code,trade_no) " +
			"values (#{orderid},#{ordertype},#{price},#{userid},#{name},#{comment},#{createtime},#{code},#{trade_no})")
	void addreceipt(Receiptvoucher rd);

	//插入收款单
	@Insert("insert into orders_incomelog (orderid,ordertype,price,userid,name,method,comment,createtime,code,trade_no，returnfeecode) " +
			"values (#{orderid},#{ordertype},#{price},#{userid},#{name},#{method},#{comment},#{createtime},#{code},#{trade_no},#{returnfeecode})")
    void addIncomelog(ReceiptDocument rd);


}
